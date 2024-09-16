package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;


import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoAsignadoPreviamenteAlAUnidadException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoSeEncontraronDueniosException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Owner;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.OwnerDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.OwnerResponseDto;

import java.util.List;

public interface OwnerService {
    List<OwnerResponseDto> findAll();

    void createOwner(OwnerDto newDuenio) throws UnitNotFoundException, DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException;

    void update(String newDocumentoDuenio, Integer id, String documentoDuenioAntiguo) throws NoSeEncontraronDueniosException;

    void remove(Integer id);

    List<Owner> dueniosPorEdificio(Integer codigo);

    List<OwnerResponseDto> findByParameter(Integer codigoUnidad, Integer idDuenio, String documento) throws NoSeEncontraronDueniosException;
}
