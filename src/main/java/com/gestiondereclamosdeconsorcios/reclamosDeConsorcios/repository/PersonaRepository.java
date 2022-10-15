package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedNativeQuery;
import javax.transaction.Transactional;
import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, String> {

    @Query("SELECT p FROM Persona p WHERE p.documento = ?1")
    @Modifying(clearAutomatically = true)
    @Transactional
    public Persona findPersonaByDocument(String documento);
}
