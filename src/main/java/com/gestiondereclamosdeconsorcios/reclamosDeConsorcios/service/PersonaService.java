package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonaRepository;

import java.util.List;

public interface PersonaService {
    List<Persona> getAll();
    void save(Persona newPersona);
    Persona update(Persona newPersona, String documento);
    void delete(String documento);
}
