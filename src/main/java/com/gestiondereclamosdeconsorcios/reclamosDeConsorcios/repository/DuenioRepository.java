package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.DuenioDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DuenioRepository extends JpaRepository<Duenio, String> {

    @Query(value = "SELECT * from duenios", nativeQuery = true)
    List<Duenio> devolverDuenios();
    Duenio findDuenioByDocumentoAndIdentificador(String documento, Integer identificadorUnidad);
    @Query(value = "SELECT d from Duenio d where d.id = ?1 ")
    Duenio findByDuenioID(Integer id);
    @Query(value = "SET NOCOUNT ON insert Into duenios (identificador, documento) values (?1, ?2) select @@identity", nativeQuery = true)
    void asignarDuenio(Integer identificador, String documento);

    /*@Query(value = "SET NOCOUNT ON update duenios d set d.identificador =?1 where d.documento =?2 select @@identity", nativeQuery = true)
    void modificarLaUnidadAsignada(Integer unidadAsignada, Integer unidadNueva,String documento);*/
    @Query(value = "DELETE FROM duenios WHERE id = ?1 select @@identity", nativeQuery=true)
    void eliminarDuenioDeLaUnidad(Integer id);

}
