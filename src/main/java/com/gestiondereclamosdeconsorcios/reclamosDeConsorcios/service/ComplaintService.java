package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.EdificioNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoEstaHabilitadoParaRealizarUnReclamo;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.SinReclamosCargadosException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamo;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ComplaintsByDocumentID;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UpdateComplaintStatusRequest;

import java.util.List;

public interface ComplaintService {

    Reclamo createReclamo(Reclamo newReclamo) throws EdificioNoEncontradoException, UnidadInexistenteException, NoEstaHabilitadoParaRealizarUnReclamo;

    List<Reclamo> getAll();

    Reclamo updateComplaintStatus(UpdateComplaintStatusRequest updateComplaintStatusRequest);

    List<Reclamo> getAllByEstado(String estado);

    List<Reclamo> getReclamos(Integer codigoEdificio, Integer codigoUnidad, Integer idReclamo) throws SinReclamosCargadosException;

    List<ComplaintsByDocumentID> getComplaints(String documentID);

    Reclamo getByID(String complaintID);
}
