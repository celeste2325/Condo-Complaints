package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InquilinoRepository extends JpaRepository<Inquilino, String> {
    @Query(value = "DELETE FROM duenios WHERE id = ?1 select @@identity", nativeQuery=true)
    void eliminarInquilinoDeLaUnidad(Integer id);

    @Query(value = "SELECT d from Duenio d where d.id = ?1 ")
    Inquilino findByInquilinoID(Integer id);
}
