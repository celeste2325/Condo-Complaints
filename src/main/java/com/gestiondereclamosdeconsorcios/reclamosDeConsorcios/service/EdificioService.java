package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;

import java.util.List;

public interface EdificioService {
    public List<Edificio> getEdificioByName(String name);
    public void saveEdificio(Edificio edificio);

    public List<Edificio>  getAll();

    public void remove(Integer id);

    public Edificio update(Edificio newEdificio, Integer id);
}



