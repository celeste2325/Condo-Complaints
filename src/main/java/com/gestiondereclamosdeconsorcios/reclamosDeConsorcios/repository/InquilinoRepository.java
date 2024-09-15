package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InquilinoRepository extends JpaRepository<Inquilino, Integer> {
    List<Inquilino> findByDocument(String document);

    boolean existsByDocumentAndUnitID(String document, Integer unitID);

    @Query(value = "SET NOCOUNT ON insert Into inquilinos (unitID, document) values (?1, ?2) select @@identity", nativeQuery = true)
    void asignarDuenio(Integer unitID, String document);

    @Query(value = "SET NOCOUNT ON delete from inquilinos where unitID =?1 select @@identity", nativeQuery = true)
    void deleteByUnitID(Integer unitID);
}
