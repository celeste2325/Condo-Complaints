package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.EdificioNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoEstaHabilitadoParaRealizarUnReclamo;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.SinReclamosCargadosException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamo;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.EdificioRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ImagenRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ReclamoRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReclamoServiceImpl implements ReclamoService {

    @Autowired
    ReclamoRepository reclamoRepository;
    @Autowired
    EdificioRepository edificioRepository;
    @Autowired
    UnidadRepository unidadRepository;

    @Autowired
    ImagenRepository imagenRepository;

    @Override
    @Transactional
    public Reclamo createReclamo(Reclamo newReclamo) throws EdificioNoEncontradoException, UnidadInexistenteException, NoEstaHabilitadoParaRealizarUnReclamo {
        boolean existEdificio = this.edificioRepository.existsById(newReclamo.getCodigoEdificio());
        boolean unidadCorrespondeAlEdificio = this.unidadRepository.existsByIdentificadorAndCodigoEdificio(newReclamo.getIdentificador(), newReclamo.getCodigoEdificio());

        if (existEdificio) {
            if (unidadCorrespondeAlEdificio) {
                List<Object[]> habilitados = this.edificioRepository.getHabilitados(newReclamo.getCodigoEdificio());
                List<Inquilino> habilitadosConv = habilitados
                        .stream()
                        .map(habilitado -> new Inquilino(((Integer) habilitado[0]), (Integer) habilitado[1], (String) habilitado[2]))
                        .collect(Collectors.toList());

                boolean esHabitanteDelEdificio = habilitadosConv.stream().filter(inquilino -> inquilino.getDocumento().equals(newReclamo.getPersonasByDocumento().getDocumento())).count() > 0;
                if (esHabitanteDelEdificio) {
                    newReclamo.getImagenesByIdReclamo().forEach(
                            imagen -> {
                                imagen.setDataFoto(imagen.getCastBlob().getBytes());
                            }
                    );
                    return this.reclamoRepository.save(newReclamo);
                } else
                    throw new NoEstaHabilitadoParaRealizarUnReclamo("El documento ingresado no pertenece a un dueño/inquilino del edificio");
            } else throw new UnidadInexistenteException("La unidad no existe en el edificio");
        } else throw new EdificioNoEncontradoException("El edificio no existe");
    }

    @Override
    public List<Reclamo> getAll() {
        return this.reclamoRepository.findAll();
    }

    @Override
    public Reclamo updateEstado(Reclamo newReclamo, Integer id) {
        return this.reclamoRepository.findById(id).map(reclamo -> {
            reclamo.setEstado(newReclamo.getEstado());
            return this.reclamoRepository.save(reclamo);
        }).get();
    }

    @Override
    public List<Reclamo> getAllByEstado(String estado) {
        return this.reclamoRepository.getByEstado(estado);
    }

    @Override
    public List<Reclamo> getReclamos(Integer codigoEdificio, Integer codigoUnidad, Integer idReclamo) throws SinReclamosCargadosException {
        List<Reclamo> reclamos = this.reclamoRepository.findAllByCodigoEdificioOrIdentificadorOrIdReclamo(codigoEdificio, codigoUnidad, idReclamo);
        if (!reclamos.isEmpty()) {
            return reclamos;
        } else throw new SinReclamosCargadosException("No existen reclamos cargados");

    }

}
