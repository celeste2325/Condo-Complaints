package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
    List<Edificio> findByNombre(String nombre);
}
