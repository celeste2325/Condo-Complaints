package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

    @Query(value = "SET NOCOUNT ON insert Into units (floor, number,buildingID) OUTPUT Inserted.identificador values (?1, ?2, ?3) select @@identity", nativeQuery = true)
    Integer saveUnit(String floor, String number, Integer buildingID);

    boolean existsByFloorAndNumberAndBuildingID(String number, String floor, Integer buildingID);

    boolean existsByUnitIDAndBuildingID(Integer unitID, Integer buildingID);

}
