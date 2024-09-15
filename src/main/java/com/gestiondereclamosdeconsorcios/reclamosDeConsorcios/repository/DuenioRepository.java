package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DuenioRepository extends JpaRepository<Duenio, Integer> {
    @Query(value = "SET NOCOUNT ON insert Into duenios (unitID, document) values (?1, ?2) select @@identity", nativeQuery = true)
    void asignarDuenio(Integer unitID, String document);

    List<Duenio> findByDocument(String document);

    boolean existsByDocumentAndUnitID(String document, Integer unitID);

    @Query(value = "select d.*, b.buildingID from duenios d " +
            "inner join units u on d.unitID = u.unitID " +
            "inner join buildings b on b.buildingID = u.buildingID " +
            "inner join people p on d.document = p.document where b.buildingID =?1 group by b.buildingID, d.id, d.unitID, d.document select @@identity", nativeQuery = true)
    List<Duenio> ownersByBuildingID(Integer buildingID);

    List<Duenio> findAllByUnitIDOrIdOrDocument(Integer unitID, Integer idDuenio, String document);

    Duenio findByDocumentAndUnitID(String documentoDuenioAntiguo, Integer id);
}
