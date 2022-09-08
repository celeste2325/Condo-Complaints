package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;

import java.util.List;

public interface EdificioService {
    public List<Edificio> getEdificioByName(String name);

    public List<Edificio>  getAll();
    public void saveEdificio(Edificio edificio);

    public void remove(Integer id);

    public Edificio update(Edificio newEdificio, Integer id);
}



