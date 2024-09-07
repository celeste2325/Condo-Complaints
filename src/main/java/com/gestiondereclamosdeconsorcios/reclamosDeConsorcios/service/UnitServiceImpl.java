package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.EdificioNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.LaUnidadYaFueCreada;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnidadDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.DuenioRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.EdificioRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnidadRepository unidadRepository;
    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private DuenioRepository dueniosRepository;

    public UnitServiceImpl(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    @Override
    public Integer saveUnidad(UnidadDto unidad) throws EdificioNoEncontradoException, LaUnidadYaFueCreada {
        if (this.edificioRepository.existsById(unidad.getCodigoEdificio())) {
            if (!this.unidadRepository.existsByPisoAndNumeroAndCodigoEdificio(unidad.getPiso(), unidad.getNumero(), unidad.getCodigoEdificio())) {
                return this.unidadRepository.saveUnidad(unidad.getPiso(), unidad.getNumero(), unidad.getCodigoEdificio());
            } else throw new LaUnidadYaFueCreada("La unidad que intenta crear ya existe en el edificio");

        } else
            throw new EdificioNoEncontradoException("El código del edificio ingresado no corresponde a un edificio del consorcio");
    }

    @Override
    public void remove(Integer id) {
        unidadRepository.deleteById(id);
    }

    @Override
    public Unidad update(Unidad newUnidad, Integer id) {
        return unidadRepository.findById(id).map(unidad -> {
            unidad.setPiso(newUnidad.getPiso());
            unidad.setNumero(newUnidad.getNumero());
            unidad.setHabitado(newUnidad.getHabitado());
            return unidadRepository.save(unidad);
        }).get();
    }

    @Override
    public List<Unidad> getAll() {
        return unidadRepository.findAll();
    }

    @Override
    public Unidad getID(Integer identificador) throws UnidadInexistenteException {
        Optional<Unidad> unidad = this.unidadRepository.findById(identificador);
        if (unidad.isPresent()) {
            return unidad.get();
        } else throw new UnidadInexistenteException("El id ingresado no corresponde a una unidad existente");
    }

}
