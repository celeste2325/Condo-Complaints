package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Building;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BuildingRepository extends JpaRepository<Building, Integer> {

    @Query(value = "Select i from Tenant i inner join Unit u on u.unitID = i.unitID where u.buildingID =?1")
    List<Tenant> getBuildingTenants(Integer buildingID);

    @Query(value = "select b.name as buildingName, b.address as buildingAddress, u.unitID as unitID, floor as floor, number as unitNumber, b.buildingID as buildingID from buildings b " +
            "inner join dbo.units u on b.buildingID = u.buildingID " +
            "inner join dbo.tenants t on u.unitID = t.unitID " +
            "where t.document = ?1", nativeQuery = true)
    List<Object[]> getBuildingByTenant(String document);

    Building findByBuildingID(Integer buildingID);
}
