package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Building;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.BuildingWithUnitsByTenant;

import java.util.List;

public interface BuildingService {

    List<Building> getAll();

    void createBuilding(Building building);

    void remove(Integer buildingID);

    Building update(Building newBuilding, Integer buildingID);

    List<Inquilino> getHabitantes(Integer buildingID);

    List<Inquilino> getHabilitados(Integer codigo);

    Building getBuilding(Integer buildingID);

    List<BuildingWithUnitsByTenant> getBuildingByTenant(String tenantDocumentID);
}



