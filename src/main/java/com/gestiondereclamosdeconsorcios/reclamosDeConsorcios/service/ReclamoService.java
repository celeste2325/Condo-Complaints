package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.EdificioNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoEstaHabilitadoParaRealizarUnReclamo;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.SinReclamosCargadosException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamo;

import java.util.List;

public interface ReclamoService {

    Reclamo createReclamo(Reclamo newReclamo) throws EdificioNoEncontradoException, UnidadInexistenteException, NoEstaHabilitadoParaRealizarUnReclamo;

    List<Reclamo> getAll();

    Reclamo updateEstado(Reclamo reclamo, Integer id);

    List<Reclamo> getAllByEstado(String estado);

    List<Reclamo> getReclamos(Integer codigoEdificio,Integer codigoUnidad,Integer idReclamo) throws SinReclamosCargadosException;

}
