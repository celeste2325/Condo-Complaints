package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Tenant;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TenantResponseDto {
    private Integer id;
    private String document;
    private String name;
    private Integer unitID;

    public TenantResponseDto(Tenant tenant) {
        this.id = tenant.getId();
        this.document = tenant.getDocument();
        this.name = tenant.getPerson().getName();
        this.unitID = tenant.getUnitID();
    }
}
