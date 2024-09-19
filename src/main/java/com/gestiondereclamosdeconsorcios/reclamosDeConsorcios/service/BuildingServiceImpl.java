package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Building;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Tenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.BuildingWithUnitsByTenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnitDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    @Transactional
    public void createBuilding(Building building) {
        buildingRepository.save(building);
    }

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    @Override
    public void deleteByID(Integer buildingID) {
        buildingRepository.deleteById(buildingID);
    }

    @Override
    public Building updateBuilding(Building newBuilding, Integer buildingID) {
        return buildingRepository.findById(buildingID).map(building -> {
            building.setAddress(newBuilding.getAddress());
            building.setName(newBuilding.getName());
            return buildingRepository.save(building);
        }).get();
    }

    @Override
    public List<Tenant> findTenantsByBuildingID(Integer buildingID) {
        return this.buildingRepository.getBuildingTenants(buildingID);
    }

    @Override
    public Building findByID(Integer buildingID) throws BuildingNotFoundException {
        Building building = this.buildingRepository.findByBuildingID(buildingID);
        if (building == null) {
            throw new BuildingNotFoundException("Building not found.");
        }
        return building;
    }

    @Override
    public List<BuildingWithUnitsByTenant> findByTenant(String tenantDocumentID) {
        List<Object[]> results = this.buildingRepository.getBuildingByTenant(tenantDocumentID);
        List<BuildingWithUnitsByTenant> buildings = new ArrayList<>();

        //// Transforms the received data into the BuildingWithUnitsByTenant DTO.
        for (Object[] row : results) {
            if (!buildings.stream()
                    .anyMatch(building -> building.getBuildingID() == (Integer) row[5])) {
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
