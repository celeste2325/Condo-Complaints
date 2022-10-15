package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InquilinoRepository extends JpaRepository<Inquilino, Integer> {
    List<Inquilino> findByDocumento(String documento);

    boolean existsByDocumentoAndIdentificador(String documento, Integer identificador);

    @Query(value = "SET NOCOUNT ON insert Into inquilinos (identificador, documento) values (?1, ?2) select @@identity", nativeQuery = true)
    void asignarDuenio(Integer identificador, String documento);
}
