package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.YaExisteUnaPersonaConMismoDniException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    void createPerson(Person newPerson) throws YaExisteUnaPersonaConMismoDniException;

    Person update(Person newPersona, String document);

    void deleteByID(String document);

    Person findByDocument(String document) throws DocumentoNoEncontradoException;
}
