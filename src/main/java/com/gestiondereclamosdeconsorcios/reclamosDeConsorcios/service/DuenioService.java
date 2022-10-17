package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;


import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.*;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.DuenioCrearDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.DuenioImpresionDto;

import java.util.List;

public interface DuenioService {
    List<DuenioImpresionDto> getAll();

    void saveDuenio(DuenioCrearDto newDuenio) throws UnidadInexistenteException, DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException;

    void remove(Integer id);

    List<Duenio> dueniosPorEdificio(Integer codigo);

    List<DuenioImpresionDto> getDuenios(Integer codigoUnidad, Integer idDuenio, String documento) throws NoSeEncontraronDueniosException;
}
