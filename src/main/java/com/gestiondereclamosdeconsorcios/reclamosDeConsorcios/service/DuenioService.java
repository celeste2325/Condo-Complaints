package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;


import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DuenioService {
    public List<Duenio> getAll();

    void saveDuenio(Duenio newDuenio);

    Duenio update(Duenio newDuenio, Integer id);

    void remove(Integer id);
}
