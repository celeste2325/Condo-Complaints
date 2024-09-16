package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Owner;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OwnerResponseDto {
    private Integer id;
    private String document;
    private String name;
    private Integer unitID;

    public OwnerResponseDto(Owner owner) {
        this.id = owner.getId();
        this.document = owner.getDocument();
        this.name = owner.getPerson().getName();
        this.unitID = owner.getUnitID();
    }
}
