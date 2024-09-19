package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.InvalidBuildingResidentException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoComplaintsFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ComplaintsByDocumentID;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UpdateComplaintStatusRequest;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.ComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaint")
@CrossOrigin(origins = "*")
public class ComplaintController {
    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/")
    public ResponseEntity<Complaint> createComplaint(@RequestBody Complaint complaint) throws BuildingNotFoundException, UnitNotFoundException, InvalidBuildingResidentException {
        return new ResponseEntity<>(this.complaintService.createComplaint(complaint), HttpStatus.CREATED);
    }

    @PutMapping("/updateComplaintStatus")
    public ResponseEntity<Complaint> updateComplaintStatus(@RequestBody UpdateComplaintStatusRequest updateComplaintStatusRequest) {
        return new ResponseEntity<>(this.complaintService.updateComplaintStatus(updateComplaintStatusRequest), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Complaint> findAll() {
        return this.complaintService.findAll();
    }

    @GetMapping("/{status}")
    public List<Complaint> findByStatus(@PathVariable String status) {
        return this.complaintService.findByStatus(status);
    }

    @GetMapping("/complaints")
    public ResponseEntity<List<Complaint>> findByParameter(@RequestParam(name = "buildingID", defaultValue = "0") Integer buildingID, @RequestParam(name = "unitID", defaultValue = "0") Integer unitID, @RequestParam(name = "complaintID", defaultValue = "0") Integer complaintID) throws NoComplaintsFoundException {
        return new ResponseEntity<>(this.complaintService.findByParameter(buildingID, unitID, complaintID), HttpStatus.OK);
    }

    @GetMapping("/complaints/tenant")
    public ResponseEntity<List<ComplaintsByDocumentID>> findByAllOrByTenant(@RequestParam(required = false) String tenantDocument) throws NoComplaintsFoundException {
        return new ResponseEntity<>(this.complaintService.findByAllOrByTenant(tenantDocument), HttpStatus.OK);
    }


}
