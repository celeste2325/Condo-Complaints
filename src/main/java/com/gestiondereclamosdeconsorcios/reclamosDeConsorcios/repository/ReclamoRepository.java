package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
    @Query(value = "select r from Reclamo r where r.estado = ?1")
    List<Reclamo> getByEstado(String estado);

    List<Reclamo> findAllByCodigoEdificioOrIdentificadorOrIdReclamo(Integer codigoEdificio, Integer codigoUnidad, Integer idReclamo);

    @Query(value = "select r.idReclamo complaintID, e.nombre as buildingName, r.ubicacion as locationIssue,\n" +
            "    r.descripcion as description, identificador as unit,\n" +
            "    r.estado as status, i.dataFoto as image\n" +
            "    from reclamos r inner join dbo.imagenes i on r.idReclamo = i.idReclamo\n" +
            "    inner join dbo.edificios e on e.codigo = r.codigo where (?1 IS NULL OR r.documento = ?1)", nativeQuery = true)
    List<Object[]> getComplaintsByTenantOrAdmin(String documentID);

    Reclamo getByIdReclamo(int complaintID);
}
