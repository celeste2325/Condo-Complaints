package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificios;

import java.util.List;

public interface EdificioService {
    List<Edificios> getEdificioByName(String name);

    List<Edificios> getAll();

    void saveEdificio(Edificios edificios);

    void remove(Integer id);

    Edificios update(Edificios newEdificios, Integer id);
}



