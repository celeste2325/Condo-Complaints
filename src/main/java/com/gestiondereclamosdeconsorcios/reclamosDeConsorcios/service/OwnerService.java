package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;


import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.OwnerAlreadyAssignedToUnitException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.OwnerNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Owner;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.OwnerDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.OwnerResponseDto;

import java.util.List;

public interface OwnerService {
    List<OwnerResponseDto> findAll();

    void assignOwnerToUnit(OwnerDto ownerDto) throws UnitNotFoundException, DocumentNotFoundException, OwnerAlreadyAssignedToUnitException;

    void assignOwnerToUnit(String newOwnerDocument, Integer unitID, String previousOwnerDocument) throws OwnerNotFoundException, DocumentNotFoundException;

    void remove(Integer id);

    List<Owner> findByBuildingID(Integer buildingID);

    List<OwnerResponseDto> findByParameter(Integer unitID, Integer ownerID, String document) throws OwnerNotFoundException;
}
