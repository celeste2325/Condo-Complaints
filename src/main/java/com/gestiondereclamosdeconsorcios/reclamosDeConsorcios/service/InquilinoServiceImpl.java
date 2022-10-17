package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoAsignadoPreviamenteAlAUnidadException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoCrearDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoImpresionDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.InquilinoRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonaRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InquilinoServiceImpl implements InquilinoService {
    @Autowired
    InquilinoRepository inquilinoRepository;

    @Autowired
    UnidadRepository unidadRepository;
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public List<InquilinoImpresionDto> getAll() {
        List<InquilinoImpresionDto> inquilinos = new ArrayList<>();
        inquilinoRepository.findAll().stream().forEach(inquilino -> {
            InquilinoImpresionDto inquilinoDto = new InquilinoImpresionDto(inquilino);
            inquilinos.add(inquilinoDto);
        });
        return inquilinos;
    }

    @Override
    public InquilinoImpresionDto getById(Integer id) throws IdInexistenteException {
        Optional<Inquilino> inquilino = this.inquilinoRepository.findById(id);
        if (inquilino.isPresent()) {
            return new InquilinoImpresionDto(inquilino.get());
        } else throw new IdInexistenteException("El id ingresado no corresponde a un inquilino registrado");
    }

    @Override
    public List<InquilinoImpresionDto> getByDocumento(String documento) throws DocumentoNoEncontradoException {
        List<InquilinoImpresionDto> inquilinosDto = new ArrayList<>();
        List<Inquilino> inquilinos = this.inquilinoRepository.findByDocumento(documento);
        if (!inquilinos.isEmpty()) {
            inquilinos.stream().forEach(inquilino -> {
                InquilinoImpresionDto inquilinoDto = new InquilinoImpresionDto(inquilino);
                inquilinosDto.add(inquilinoDto);
            });
        } else throw new DocumentoNoEncontradoException("El dni ingresado no corresponde a un inquilino del consorcio");

        return inquilinosDto;
    }

    @Override
    public void liberarUnidad(Integer identificadorUnidad, Integer codigoEdificio) throws UnidadInexistenteException {
        Optional<Unidad> unidad = this.unidadRepository.findById(identificadorUnidad);

        if (unidad.isPresent()) {
            if (unidad.get().getCodigoEdificio() == codigoEdificio) {
                this.inquilinoRepository.deleteByIdentificador(identificadorUnidad);
                unidad.get().setHabitado("N");
                this.unidadRepository.save(unidad.get());
            } else throw new UnidadInexistenteException("No existe una unidad en el edificio ingresado");

        } else throw new UnidadInexistenteException("Unidad inexistente");

    }

    @Transactional
    @Override
    public void save(InquilinoCrearDto newInquilino) throws DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException, UnidadInexistenteException {
        //para validar si la persona a la que se quiere asignar como dueño ya existe en la base
        boolean existeLaPersona = this.personaRepository.existsById(newInquilino.getDocumento());

        //para validar si existe la unidad a la cual quiere asignar el dueño.
        Optional<Unidad> unidad = this.unidadRepository.findById(newInquilino.getIdentificador());

        //para validar si el dueño a asignar ya fue asignado a esa misma unidad
        boolean yaFueAsignado = this.inquilinoRepository.existsByDocumentoAndIdentificador(newInquilino.getDocumento(), unidad.get().getIdentificador());

        if (existeLaPersona) {
            if (unidad.isPresent()) {
                if (!yaFueAsignado) {
                    unidad.get().setHabitado("S");
                    this.unidadRepository.save(unidad.get());
                    this.inquilinoRepository.asignarDuenio(newInquilino.getIdentificador(), newInquilino.getDocumento());
                    System.out.println(unidad.get().getHabitado());
                } else
                    throw new DocumentoAsignadoPreviamenteAlAUnidadException("Ya el inquilino fue asignado previamente a la unidad");
            } else
                throw new UnidadInexistenteException("La unidad no existe");
        } else
            throw new DocumentoNoEncontradoException("El documento del inquilino que desea asignar no corresponde a una persona registrada en el consorcio");
    }

    @Override
    public void delete(Integer id) {
        this.inquilinoRepository.deleteById(id);

    }


}
