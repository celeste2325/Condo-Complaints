package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImagenRepository extends JpaRepository<Imagen, Integer> {

    @Query(value = "select i from Imagen i where i.reclamosByIdReclamo.idReclamo =?1")
    Imagen getImageByComplaintID(int complaintID);
}
