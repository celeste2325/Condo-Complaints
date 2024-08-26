package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnitDto {
    private int unitID;
    private String unitNumber;
    private String floor;

    public UnitDto(int unitID, String floor, String unitNumber) {
        this.unitID = unitID;
        this.floor = floor;
        this.unitNumber = unitNumber;
    }

    public UnitDto() {
    }
}
