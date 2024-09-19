package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.OwnerAlreadyAssignedToUnitException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.TenantDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.TenantResponseDto;

import java.util.List;

public interface TenantService {

    void assignTenantToUnit(TenantDto newTenant) throws UnitNotFoundException, DocumentNotFoundException, OwnerAlreadyAssignedToUnitException;

    List<TenantResponseDto> findAll();

    TenantResponseDto findByID(Integer id) throws IdNotFoundException;

    List<TenantResponseDto> findByDocument(String document) throws DocumentNotFoundException;

    void releaseUnit(Integer unitID, Integer buildingID) throws UnitNotFoundException;

    void delete(Integer id);
}
