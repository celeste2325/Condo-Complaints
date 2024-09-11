package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateComplaintStatusRequest {
    private String newStatus;
    private String complaintID;
}
