package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EdificioRepository extends JpaRepository<Edificios, Integer> {
    List<Edificios> findByNombre(String nombre);
}
