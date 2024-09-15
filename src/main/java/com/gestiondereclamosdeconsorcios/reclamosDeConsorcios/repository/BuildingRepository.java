package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Building;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BuildingRepository extends JpaRepository<Building, Integer> {

    @Query(value = "Select i from Inquilino i inner join Unit u on u.unitID = i.unitID where u.buildingID =?1")
    List<Inquilino> getHabitantes(Integer buildingID);

    @Query(value = "select uni.*, u.buildingID from (select * from inquilinos union all  SELECT * from duenios) as uni inner join units u on uni.unitID = u.unitID inner join buildings b on b.buildingID = u.buildingID\n" +
            "where b.buildingID=?1", nativeQuery = true)
    List<Object[]> getHabilitados(Integer buildingID);


    @Query(value = "select b.name as buildingName, b.address as buildingAddress, u.unitID as unitID, floor as floor, number as unitNumber, b.buildingID as buildingID from buildings b " +
            "inner join dbo.units u on b.buildingID = u.buildingID " +
            "inner join dbo.inquilinos i on u.unitID = i.unitID " +
            "where i.document = ?1", nativeQuery = true)
    List<Object[]> getBuildingByTenant(String document);

    Building findByBuildingID(Integer buildingID);
}
