package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.*;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoCrearDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoImpresionDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.InquilinoRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonaRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        inquilinoRepository.findAll().stream().forEach(inquilino ->{
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
        }else throw new IdInexistenteException("El id ingresado no corresponde a un inquilino registrado");
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
    public void save(InquilinoCrearDto newInquilino) throws UnidadInexistenteException, UnidadOcupadaException, DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException {
        //para validar si la persona a la que se quiere asignar como dueño ya existe en la base
        Optional<Persona> personaEncontrada = this.personaRepository.findById(newInquilino.getDocumento());
        if (this.losDatosSonValidos(newInquilino)) {
            if (personaEncontrada.isPresent()) {
                this.inquilinoRepository.asignarDuenio(newInquilino.getIdentificador(), newInquilino.getDocumento());
            } else
                throw new DocumentoNoEncontradoException("El documento del inquilino que desea asignar no corresponde a una persona registrada en el consorcio");
        }
    }
    private boolean losDatosSonValidos(InquilinoCrearDto inquilinoNvo) throws DocumentoAsignadoPreviamenteAlAUnidadException {
        //para validar si existe la unidad a la cual quiere asignar el dueño.
        Optional<Unidad> unidadEncontrada = this.unidadRepository.findById(inquilinoNvo.getIdentificador());

        //para validar si el dueño a asignar ya fue asignado a esa misma unidad
        boolean yaFueAsignado = this.inquilinoRepository.existsByDocumentoAndIdentificador(inquilinoNvo.getDocumento(), unidadEncontrada.get().getIdentificador());
        if (unidadEncontrada.isPresent()) {
            if (!yaFueAsignado) {
                return true;
            } else
                throw new DocumentoAsignadoPreviamenteAlAUnidadException("Ya el inquilino fue asignado previamente a la unidad");
        }
        return false;

    }

    @Override
    public void delete(Integer id) {
        this.inquilinoRepository.deleteById(id);

    }


}
