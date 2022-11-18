package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.YaExisteUnaPersonaConMismoDniException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;

import java.util.List;

public interface PersonaService {
    List<Persona> getAll();

    void save(Persona newPersona) throws YaExisteUnaPersonaConMismoDniException;

    Persona update(Persona newPersona, String documento);

    void delete(String documento);

    boolean existePersonaByDocumento(String documento) throws DocumentoNoEncontradoException;
}
