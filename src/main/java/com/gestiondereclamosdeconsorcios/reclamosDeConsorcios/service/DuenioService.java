package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;


import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DuenioAsignadoPreviamenteAlAUnidadException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.DuenioDto;

import java.util.List;

public interface DuenioService {
    List<Duenio> getAll();

    void saveDuenio(DuenioDto newDuenio) throws UnidadInexistenteException, DocumentoNoEncontradoException, DuenioAsignadoPreviamenteAlAUnidadException;

    //void modificarLaUnidadAsignada(Integer unidadAsignada, Integer unidadNueva, String documento) throws DuenioAsignadoPreviamenteAlAUnidadException, DocumentoNoEncontradoException;

    void remove(Integer id);
}
