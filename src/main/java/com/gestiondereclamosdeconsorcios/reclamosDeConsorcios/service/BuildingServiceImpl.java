package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Building;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.BuildingWithUnitsByTenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnitDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    @Transactional
    public void createBuilding(Building building) {
        buildingRepository.save(building);
    }

    @Override
    public List<Building> getAll() {
        return buildingRepository.findAll();
    }

    @Override
    public void remove(Integer buildingID) {
        buildingRepository.deleteById(buildingID);
    }

    @Override
    public Building update(Building newBuilding, Integer buildingID) {
        return buildingRepository.findById(buildingID).map(edificio -> {
            edificio.setAddress(newBuilding.getAddress());
            edificio.setName(newBuilding.getName());
            return buildingRepository.save(edificio);
        }).get();
    }

    @Override
    public List<Inquilino> getHabitantes(Integer buildingID) {
        return this.buildingRepository.getHabitantes(buildingID);
    }

    @Override
    public List<Inquilino> getHabilitados(Integer codigo) {
        List<Object[]> habilitados = this.buildingRepository.getHabilitados(codigo);
        return habilitados
                .stream()
                .map(habilitado -> new Inquilino(((Integer) habilitado[0]), (Integer) habilitado[1], (String) habilitado[2]))
                .collect(Collectors.toList());
    }

    @Override
    public Building getBuilding(Integer buildingID) {
        return this.buildingRepository.findByBuildingID(buildingID);
    }

    @Override
    public List<BuildingWithUnitsByTenant> getBuildingByTenant(String tenantDocumentID) {
        List<Object[]> results = this.buildingRepository.getBuildingByTenant(tenantDocumentID);
        List<BuildingWithUnitsByTenant> buildings = new ArrayList<>();

        //// Transforms the received data into the BuildingWithUnitsByTenant DTO.
        for (Object[] row : results) {
            if (!buildings.stream()
                    .anyMatch(buil -> buil.getBuildingID() == (Integer) row[5])) {
                BuildingWithUnitsByTenant building = new BuildingWithUnitsByTenant(
                        (String) row[0],    // buildingName
                        (String) row[1],    // buildingAddress
                        (Integer) row[2],   // unitID
                        (String) row[3],    // floor
                        (String) row[4],    // unitNumber
                        (Integer) row[5]    // buildingID
                );
                buildings.add(building);
            } else {
                //add the unit to the existing building
                for (BuildingWithUnitsByTenant building : buildings) {
                    if (building.getBuildingID() == (Integer) row[5]) {
                        UnitDto unit = new UnitDto((Integer) row[2], (String) row[3], (String) row[4]);
                        building.getUnits().add(unit);
                    }
                }
            }
        }
        return buildings;
    }
}
