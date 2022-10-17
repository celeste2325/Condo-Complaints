package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
    @Query(value = "select r from Reclamo r where r.estado = ?1")
    List<Reclamo> getByEstado(String estado);

    List<Reclamo> findAllByCodigoEdificioOrIdentificadorOrIdReclamo(Integer codigoEdificio, Integer codigoUnidad, Integer idReclamo);
}
