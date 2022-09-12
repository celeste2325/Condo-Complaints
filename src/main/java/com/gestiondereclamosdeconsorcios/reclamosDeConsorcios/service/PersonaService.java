package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;

import java.util.List;

public interface PersonaService {
    List<Persona> getAll();
    void save(Persona newPersona);
    Persona update(Persona newPersona, String documento);
    void delete(String documento);
}
