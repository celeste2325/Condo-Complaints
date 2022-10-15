package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReclamoRepository extends JpaRepository<Reclamos, Integer> {
    @Query(value = "select r from Reclamos r where r.estado = ?1")
    List<Reclamos> getByEstado(String estado);
}
