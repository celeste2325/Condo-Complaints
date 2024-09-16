package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.InvalidBuildingResidentException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoComplaintsFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ComplaintsByDocumentID;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UpdateComplaintStatusRequest;

import java.util.List;

public interface ComplaintService {

    Complaint createComplaint(Complaint complaint) throws BuildingNotFoundException, UnitNotFoundException, InvalidBuildingResidentException;

    List<Complaint> findAll();

    Complaint updateComplaintStatus(UpdateComplaintStatusRequest updateComplaintStatusRequest);

    List<Complaint> findByStatus(String status);

    List<Complaint> findByParameter(Integer buildingID, Integer unitID, Integer complaintID) throws NoComplaintsFoundException;

    List<ComplaintsByDocumentID> findByAllOrByTenant(String document) throws NoComplaintsFoundException;

    Complaint getByID(String complaintID);
}
