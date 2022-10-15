package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InquilinoRepository extends JpaRepository<Inquilino, Integer> {

    @Query("select i from Inquilino i where i.documento = ?1 group by i.id , i.documento, i.contrasenia,i.nombre,i.roles,i.identificador")
    List<Inquilino> findDistinctByDocumento(String documento);


}
