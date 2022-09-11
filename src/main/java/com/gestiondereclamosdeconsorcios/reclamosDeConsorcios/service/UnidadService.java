package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;

import java.util.List;

public interface UnidadService {
    void saveUnidad(Unidad unidad);

    void remove(Integer id);

    Unidad update(Unidad newUnidad, Integer id);

    List<Unidad> getAll();

}
