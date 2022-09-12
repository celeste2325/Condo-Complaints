package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;


import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;

import java.util.List;

public interface DuenioService {
    List<Duenio> getAll();
    void saveDuenio(Duenio newDuenio);
    Duenio update(Duenio newDuenio, Integer id);
    void remove(Integer id);
}
