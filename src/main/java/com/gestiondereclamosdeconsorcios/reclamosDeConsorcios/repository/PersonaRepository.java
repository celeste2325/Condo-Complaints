package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, String> {
}
