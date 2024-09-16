package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.*;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.TenantDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.TenantResponseDto;

import java.util.List;

public interface TenantService {

    void createTenant(TenantDto newTenant) throws UnitNotFoundException, UnidadOcupadaException, DocumentoNoEncontradoException, DocumentoAsignadoPreviamenteAlAUnidadException;

    List<TenantResponseDto> findAll();

    TenantResponseDto findByID(Integer id) throws IdInexistenteException;

    List<TenantResponseDto> findByDocument(String document) throws DocumentoNoEncontradoException;

    void releaseUnit(Integer unitID, Integer buildingID) throws UnitNotFoundException;

    void delete(Integer id);
}
