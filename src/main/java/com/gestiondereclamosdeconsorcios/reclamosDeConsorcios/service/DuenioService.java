package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;


import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenios;

import java.util.List;

public interface DuenioService {
    List<Duenios> getAll();
    void saveDuenio(Duenios newDuenios);
    Duenios update(Duenios newDuenios, Integer id);
    void remove(Integer id);
}
