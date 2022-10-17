package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DuenioRepository extends JpaRepository<Duenio,Integer> {
    @Query(value = "SET NOCOUNT ON insert Into duenios (identificador, documento) values (?1, ?2) select @@identity", nativeQuery = true)
    void asignarDuenio(Integer identificador, String documento);

    List<Duenio> findByDocumento(String documento);

    boolean existsByDocumentoAndIdentificador(String documento, Integer identificador);

    @Query(value = "select d.*, e.codigo from duenios d " +
            "inner join unidades u on d.identificador = u.identificador " +
            "inner join edificios e on e.codigo = u.codigoEdificio " +
            "inner join personas p on d.documento = p.documento where e.codigo =?1 group by e.codigo, d.id, d.identificador, d.documento select @@identity", nativeQuery = true)
    List<Duenio> dueniosPorEdificio(Integer codigo);

    List<Duenio> findAllByIdentificadorOrIdOrDocumento(Integer codigoUnidad, Integer idDuenio, String documento);
}
