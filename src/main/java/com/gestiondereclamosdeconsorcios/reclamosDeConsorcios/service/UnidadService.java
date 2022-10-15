package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidades;

import java.util.List;

public interface UnidadService {
    void saveUnidad(Unidades unidad);

    void remove(Integer id);

    Unidades update(Unidades newUnidad, Integer id);

    List<Unidades> getAll();

}
