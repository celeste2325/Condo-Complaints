package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

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

    List<Tenant> getHabilitados(Integer codigo);

    Building findByID(Integer buildingID);

    List<BuildingWithUnitsByTenant> findByTenant(String tenantDocumentID);
}



