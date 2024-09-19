package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    @Query(value = "SET NOCOUNT ON insert Into owners (unitID, document) values (?1, ?2) select @@identity", nativeQuery = true)
    void assignOwnerToUnit(Integer unitID, String document);

    List<Owner> findByDocument(String document);

    boolean existsByDocumentAndUnitID(String document, Integer unitID);

    @Query(value = "select o.*, b.buildingID from owners o " +
            "inner join units u on o.unitID = u.unitID " +
            "inner join buildings b on b.buildingID = u.buildingID " +
            "inner join people p on o.document = p.document where b.buildingID =?1 group by b.buildingID, o.id, o.unitID, o.document select @@identity", nativeQuery = true)
    List<Owner> ownersByBuildingID(Integer buildingID);

    List<Owner> findAllByUnitIDOrIdOrDocument(Integer unitID, Integer id, String document);

    Owner findByDocumentAndUnitID(String previousOwnerDocument, Integer unitID);
}
