package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;


import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoAsignadoPreviamenteAlAUnidadException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.DuenioCrearDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.DuenioImpresionDto;

import java.util.List;

public interface DuenioService {
    List<DuenioImpresionDto> getAll();

    void saveDuenio(DuenioCrearDto newDuenio) throws UnidadInexistenteException, DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException;

    void remove(Integer id);

    DuenioImpresionDto getById(Integer id) throws IdInexistenteException;

    List<DuenioImpresionDto> getByDocumento(String documento) throws DocumentoNoEncontradoException;
}
