package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.InvalidBuildingResidentException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoComplaintsFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Tenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ComplaintsByDocumentID;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UpdateComplaintStatusRequest;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.BuildingRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.ComplaintRepository;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.UnitRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final BuildingRepository buildingRepository;
    private final UnitRepository unitRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository, BuildingRepository buildingRepository, UnitRepository unitRepository) {
        this.complaintRepository = complaintRepository;
        this.buildingRepository = buildingRepository;
        this.unitRepository = unitRepository;
    }

    @Override
    @Transactional
    public Complaint createComplaint(Complaint complaint) throws BuildingNotFoundException, UnitNotFoundException, InvalidBuildingResidentException {
        boolean buildingExists = this.buildingRepository.existsById(complaint.getBuildingID());
        boolean unitsExists = this.unitRepository.existsByUnitIDAndBuildingID(complaint.getUnitID(), complaint.getBuildingID());
        Complaint complaintCreated;
        if (buildingExists) {
            if (unitsExists) {
                List<Object[]> habilitados = this.buildingRepository.getHabilitados(complaint.getBuildingID());
                List<Tenant> habilitadosConv = habilitados
                        .stream()
                        .map(habilitado -> new Tenant(((Integer) habilitado[0]), (Integer) habilitado[1], (String) habilitado[2]))
                        .collect(Collectors.toList());

                boolean esHabitanteDelEdificio = habilitadosConv.stream().filter(inquilino -> inquilino.getDocument().equals(complaint.getPersonByDocument().getDocument())).count() > 0;
                if (esHabitanteDelEdificio) {
                    complaintCreated = this.complaintRepository.save(complaint);
                    complaint.getImagesByComplaintID().forEach(
                            imagen -> {
                                imagen.setPath("assets//" + complaintCreated.getComplaintID() + "//" + imagen.getPath());
                                imagen.setExtension(imagen.getExtension());
                            }
                    );

                } else
                    throw new InvalidBuildingResidentException("The entered document does not belong to an owner/tenant of the building.");
            } else throw new UnitNotFoundException("Unit not found");
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
        List<Complaint> reclamos = this.complaintRepository.findAllByBuildingIDOrUnitIDOrComplaintID(buildingID, unitID, complaintID);
        if (!reclamos.isEmpty()) {
            return reclamos;
        } else throw new NoComplaintsFoundException("You have not submitted any complaints yet.");

    }

    @Override
    public List<ComplaintsByDocumentID> findByAllOrByTenant(String document) throws NoComplaintsFoundException {
        List<Object[]> results = this.complaintRepository.getAllComplaintsOrByTenant(document);

        if (!results.isEmpty()) {
            List<ComplaintsByDocumentID> complaints = new ArrayList<>();

            //// Transforms the received data into the BuildingWithUnitsByTenant DTO.
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
