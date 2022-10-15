package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PersonaRepository extends JpaRepository<Personas, String> {

    @Query("SELECT p FROM Personas p WHERE p.documento = ?1")
    @Modifying(clearAutomatically = true)
    @Transactional
    public Personas findPersonaByDocument(String documento);
}
