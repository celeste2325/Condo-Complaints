package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;

import java.util.List;

public interface UnidadService {
    public void saveUnidad(Unidad unidad);

    public void remove(Integer id);

    public Unidad update(Unidad newUnidad, Integer id);

    public List<Unidad> getAll();

}
