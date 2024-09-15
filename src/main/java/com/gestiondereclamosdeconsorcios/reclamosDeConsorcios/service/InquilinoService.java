package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.*;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoCrearDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoImpresionDto;

import java.util.List;

public interface InquilinoService {
    List<InquilinoImpresionDto> getAll();

    void save(InquilinoCrearDto newInquilino) throws UnitNotFoundException, UnidadOcupadaException, DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException;

    void delete(Integer id);

    InquilinoImpresionDto getById(Integer id) throws IdInexistenteException;

    List<InquilinoImpresionDto> getByDocumento(String documento) throws DocumentoNoEncontradoException;


    void liberarUnidad(Integer identificadorUnidad, Integer codigoEdificio) throws UnitNotFoundException;
}
