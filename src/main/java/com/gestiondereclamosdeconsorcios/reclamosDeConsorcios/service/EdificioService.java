package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;

import java.util.List;

public interface EdificioService {
    public List<Edificio> getEdificioByName(String name);
}



