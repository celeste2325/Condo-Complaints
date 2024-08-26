package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.EdificioNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.LaUnidadYaFueCreada;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnidadDto;

import java.util.List;

public interface UnidadService {
    Integer saveUnidad(UnidadDto unidad) throws EdificioNoEncontradoException, LaUnidadYaFueCreada;

    void remove(Integer id);

    Unidad update(Unidad newUnidad, Integer id);

    List<Unidad> getAll();

    Unidad getID(Integer identificador) throws UnidadInexistenteException;

}
