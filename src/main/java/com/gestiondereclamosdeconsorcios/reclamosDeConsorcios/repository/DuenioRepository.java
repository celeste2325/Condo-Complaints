package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DuenioRepository extends JpaRepository<Duenio,Integer> {
    @Query(value = "SET NOCOUNT ON insert Into duenios (identificador, documento) values (?1, ?2) select @@identity", nativeQuery = true)
    void asignarDuenio(Integer identificador, String documento);

    List<Duenio> findByDocumento(String documento);

    boolean existsByDocumentoAndIdentificador(String documento, Integer identificador);
}
