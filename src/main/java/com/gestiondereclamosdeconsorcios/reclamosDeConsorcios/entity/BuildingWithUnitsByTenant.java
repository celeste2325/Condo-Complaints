package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnitDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class BuildingWithUnitsByTenant {
    private String buildingName;
    private String buildingAddress;
    private int buildingID;
    private List<UnitDto> units;

    public BuildingWithUnitsByTenant(String buildingName, String buildingAddress, int unitID, String floor, String unitNumber, int buildingID) {
        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.buildingAddress = buildingAddress;
        this.units = new ArrayList<>();
        UnitDto unit = new UnitDto(unitID, floor, unitNumber);
        this.units.add(unit);
    }
}
