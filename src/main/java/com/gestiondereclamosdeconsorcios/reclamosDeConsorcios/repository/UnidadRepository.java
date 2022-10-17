package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnidadRepository extends JpaRepository<Unidad, Integer> {

    @Query(value = "SET NOCOUNT ON insert Into unidades (piso, numero,codigoEdificio) OUTPUT Inserted.identificador values (?1, ?2, ?3) select @@identity", nativeQuery = true)
    Integer saveUnidad(String piso, String numero, Integer codigoEdificio);

    boolean existsByPisoAndNumeroAndCodigoEdificio(String numero, String piso, Integer codigoEdificio);

    boolean existsByIdentificadorAndCodigoEdificio(Integer identificador, Integer codigoEdificio);

}
