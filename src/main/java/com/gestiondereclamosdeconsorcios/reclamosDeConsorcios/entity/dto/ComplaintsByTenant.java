package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ComplaintsByTenant {
    private int complaintID;
    private String buildingName;
    private String locationIssue;
    private String description;
    private int unit;
    private String status;
    private String image;

    public ComplaintsByTenant(int complaintID, String buildingName, String locationIssue, String description, int unit, String status, String image) {
        this.complaintID = complaintID;
        this.buildingName = buildingName;
        this.locationIssue = locationIssue;
        this.description = description;
        this.unit = unit;
        this.status = status;
        this.image = image;
    }
}
