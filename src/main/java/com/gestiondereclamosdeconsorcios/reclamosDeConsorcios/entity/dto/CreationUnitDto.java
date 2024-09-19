package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreationUnitDto {
    private String floor;
    private String number;
    private Integer buildingID;
    private String occupied = "N";
}
