package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoAsignadoPreviamenteAlAUnidadException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoCrearDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoImpresionDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.InquilinoRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnitRepository;
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
    UnitRepository unitRepository;
    @Autowired
    PersonRepository personRepository;

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
        List<Inquilino> inquilinos = this.inquilinoRepository.findByDocument(documento);
        if (!inquilinos.isEmpty()) {
            inquilinos.stream().forEach(inquilino -> {
                InquilinoImpresionDto inquilinoDto = new InquilinoImpresionDto(inquilino);
                inquilinosDto.add(inquilinoDto);
            });
        } else throw new DocumentoNoEncontradoException("El dni ingresado no corresponde a un inquilino del consorcio");

        return inquilinosDto;
    }

    @Override
    public void liberarUnidad(Integer identificadorUnidad, Integer codigoEdificio) throws UnitNotFoundException {
        Optional<Unit> unidad = this.unitRepository.findById(identificadorUnidad);

        if (unidad.isPresent()) {
            if (unidad.get().getBuildingID() == codigoEdificio) {
                this.inquilinoRepository.deleteByUnitID(identificadorUnidad);
                unidad.get().setHabitado("N");
                this.unitRepository.save(unidad.get());
            } else throw new UnitNotFoundException("No existe una unidad en el edificio ingresado");

        } else throw new UnitNotFoundException("Unidad inexistente");

    }

    @Transactional
    @Override
    public void save(InquilinoCrearDto newInquilino) throws DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException, UnitNotFoundException {
        //para validar si la persona a la que se quiere asignar como dueño ya existe en la base
        boolean existeLaPersona = this.personRepository.existsById(newInquilino.getDocumento());

        //para validar si existe la unidad a la cual quiere asignar el dueño.
        Optional<Unit> unidad = this.unitRepository.findById(newInquilino.getIdentificador());

        //para validar si el dueño a asignar ya fue asignado a esa misma unidad
        boolean yaFueAsignado = this.inquilinoRepository.existsByDocumentAndUnitID(newInquilino.getDocumento(), unidad.get().getUnitID());

        if (existeLaPersona) {
            if (unidad.isPresent()) {
                if (!yaFueAsignado) {
                    unidad.get().setHabitado("S");
                    this.unitRepository.save(unidad.get());
                    this.inquilinoRepository.asignarDuenio(newInquilino.getIdentificador(), newInquilino.getDocumento());
                    System.out.println(unidad.get().getHabitado());
                } else
                    throw new DocumentoAsignadoPreviamenteAlAUnidadException("Ya el inquilino fue asignado previamente a la unidad");
            } else
                throw new UnitNotFoundException("La unidad no existe");
        } else
            throw new DocumentoNoEncontradoException("El documento del inquilino que desea asignar no corresponde a una persona registrada en el consorcio");
    }

    @Override
    public void delete(Integer id) {
        this.inquilinoRepository.deleteById(id);

    }


}
