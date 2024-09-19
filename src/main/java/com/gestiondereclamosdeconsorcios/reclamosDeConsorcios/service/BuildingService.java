package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Building;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Tenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.BuildingWithUnitsByTenant;

import java.util.List;

public interface BuildingService {

    List<Building> findAll();

    void createBuilding(Building building);

    void deleteByID(Integer buildingID);

    Building updateBuilding(Building newBuilding, Integer buildingID);

    List<Tenant> findTenantsByBuildingID(Integer buildingID);

    Building findByID(Integer buildingID) throws BuildingNotFoundException;

    List<BuildingWithUnitsByTenant> findByTenant(String tenantDocumentID);
}



