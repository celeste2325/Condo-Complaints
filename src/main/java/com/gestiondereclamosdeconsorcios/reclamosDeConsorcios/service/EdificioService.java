package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;

import java.util.List;

public interface EdificioService {
    List<Edificio> getEdificioByName(String name);

    List<Edificio> getAll();

    void saveEdificio(Edificio edificio);

    void remove(Integer id);

    Edificio update(Edificio newEdificio, Integer id);
}



