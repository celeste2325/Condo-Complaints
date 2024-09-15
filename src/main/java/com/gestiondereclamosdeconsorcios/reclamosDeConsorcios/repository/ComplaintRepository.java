package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    @Query(value = "select c from Complaint c where c.status = ?1")
    List<Complaint> getByStatus(String status);

    List<Complaint> findAllByBuildingIDOrUnitIDOrComplaintID(Integer buildingID, Integer unitID, Integer complaintID);

    @Query(value = "select c.complaintID as complaintID, b.name as buildingName, c.location as locationIssue,\n" +
            "    c.description as description, c.unitID as unit,\n" +
            "    c.status as status, i.path as image\n" +
            "    from complaints c inner join dbo.images i on c.complaintID = i.complaintID\n" +
            "    inner join dbo.buildings b on b.buildingID = c.buildingID where (?1 IS NULL OR c.document = ?1)", nativeQuery = true)
    List<Object[]> getComplaintsByTenantOrAdmin(String documentID);

    Complaint getByComplaintID(int complaintID);
}
