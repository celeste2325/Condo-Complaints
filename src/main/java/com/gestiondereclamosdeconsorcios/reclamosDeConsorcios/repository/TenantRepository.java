package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    List<Tenant> findByDocument(String document);

    boolean existsByDocumentAndUnitID(String document, Integer unitID);

    @Query(value = "SET NOCOUNT ON insert Into tenants (unitID, document) values (?1, ?2) select @@identity", nativeQuery = true)
    void assignTenant(Integer unitID, String document);

    @Query(value = "SET NOCOUNT ON delete from tenants where unitID =?1 select @@identity", nativeQuery = true)
    void deleteByUnitID(Integer unitID);
}
