package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.InvalidBuildingResidentException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoComplaintsFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ComplaintsByDocumentID;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UpdateComplaintStatusRequest;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.BuildingRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ComplaintRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.TenantRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnitRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final BuildingRepository buildingRepository;
    private final UnitRepository unitRepository;
    private final TenantRepository tenantRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository, BuildingRepository buildingRepository, UnitRepository unitRepository, TenantRepository tenantRepository) {
        this.complaintRepository = complaintRepository;
        this.buildingRepository = buildingRepository;
        this.unitRepository = unitRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    @Transactional
    public Complaint createComplaint(Complaint complaint) throws BuildingNotFoundException, UnitNotFoundException, InvalidBuildingResidentException {
        boolean buildingExists = this.buildingRepository.existsById(complaint.getBuildingID());
        Complaint complaintCreated;
        if (buildingExists) {
            boolean unitExists = this.unitRepository.existsByUnitIDAndBuildingID(complaint.getUnitID(), complaint.getBuildingID());
            if (unitExists) {
                boolean isTenantOfTheUnit = this.tenantRepository.existsByDocumentAndUnitID(complaint.getPersonByDocument().getDocument(), complaint.getUnitID());
                if (isTenantOfTheUnit) {
                    complaintCreated = this.complaintRepository.save(complaint);
                    complaint.getImagesByComplaintID().forEach(
                            image -> {
                                image.setPath("assets//" + complaintCreated.getComplaintID() + "//" + image.getPath());
                                image.setExtension(image.getExtension());
                            }
                    );

                } else
                    throw new InvalidBuildingResidentException("invalid resident of the unit");
            } else throw new UnitNotFoundException("Unit not found in the building");
        } else throw new BuildingNotFoundException("Building not found.");
        return complaintCreated;
    }

    @Override
    public List<Complaint> findAll() {
        return this.complaintRepository.findAll();
    }

    @Override
    public Complaint updateComplaintStatus(UpdateComplaintStatusRequest updateComplaintStatusRequest) {
        return this.complaintRepository.findById(Integer.parseInt(updateComplaintStatusRequest.getComplaintID())).map(complaint -> {
            complaint.setStatus(updateComplaintStatusRequest.getNewStatus());
            return this.complaintRepository.save(complaint);
        }).get();
    }

    @Override
    public List<Complaint> findByStatus(String status) {
        return this.complaintRepository.getByStatus(status);
    }

    @Override
    public List<Complaint> findByParameter(Integer buildingID, Integer unitID, Integer complaintID) throws NoComplaintsFoundException {
        List<Complaint> complaints = this.complaintRepository.findAllByBuildingIDOrUnitIDOrComplaintID(buildingID, unitID, complaintID);
        if (!complaints.isEmpty()) {
            return complaints;
        } else throw new NoComplaintsFoundException("No complaints were found.");

    }

    @Override
    public List<ComplaintsByDocumentID> findByAllOrByTenant(String document) throws NoComplaintsFoundException {
        List<Object[]> results = this.complaintRepository.getAllComplaintsOrByTenant(document);

        if (!results.isEmpty()) {
            List<ComplaintsByDocumentID> complaints = new ArrayList<>();

            // Transforms the received data into the BuildingWithUnitsByTenant DTO.
            for (Object[] row : results) {
                ComplaintsByDocumentID complaintsByTenant = new ComplaintsByDocumentID(
                        (Integer) row[0],     // complaintID
                        (String) row[1],     // buildingName
                        (String) row[2],     // locationIssue
                        (String) row[3],     // description
                        (Integer) row[4],    // unit
                        (String) row[5],     // status
                        (String) row[6]     // image
                );
                complaints.add(complaintsByTenant);
            }
            return complaints;
        } else throw new NoComplaintsFoundException("You have not submitted any complaints yet.");

    }

    public Complaint getByID(String complaintID) {
        return this.complaintRepository.getByComplaintID(Integer.parseInt(complaintID));
    }
}
