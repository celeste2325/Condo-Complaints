package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoComplaintsFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ComplaintsByDocumentID;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UpdateComplaintStatusRequest;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaint")
@CrossOrigin(origins = "*")
public class ComplaintController {
    @Autowired
    ComplaintService complaintService;

    @PostMapping("/")
    public ResponseEntity createComplaint(@RequestBody Complaint complaint) {
        try {
            return new ResponseEntity<>(this.complaintService.createComplaint(complaint), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<Complaint> getComplaints() {
        return this.complaintService.getAll();
    }

    @GetMapping("/{status}")
    public List<Complaint> getComplaintsByStatus(@PathVariable String status) {
        return this.complaintService.getAllByStatus(status);
    }

    @GetMapping("/getComplaints")
    public ResponseEntity getComplaints(@RequestParam(name = "buildingID", defaultValue = "0") Integer buildingID, @RequestParam(name = "unitID", defaultValue = "0") Integer unitID, @RequestParam(name = "complaintID", defaultValue = "0") Integer complaintID) {
        try {
            return new ResponseEntity<>(this.complaintService.getComplaints(buildingID, unitID, complaintID), HttpStatus.OK);
        } catch (NoComplaintsFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getComplaintsByTenantOrAdmin")
    public ResponseEntity<List<ComplaintsByDocumentID>> getComplaints(@RequestParam(required = false) String tenantDocument) throws NoComplaintsFoundException {
        return new ResponseEntity<>(this.complaintService.getComplaintsByDocument(tenantDocument), HttpStatus.OK);
    }

    @PutMapping("/updateComplaintStatus")
    public ResponseEntity updateComplaintStatus(@RequestBody UpdateComplaintStatusRequest updateComplaintStatusRequest) {
        return new ResponseEntity(this.complaintService.updateComplaintStatus(updateComplaintStatusRequest), HttpStatus.OK);
    }


}
