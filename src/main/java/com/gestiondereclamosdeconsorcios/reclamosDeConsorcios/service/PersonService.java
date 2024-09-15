package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.YaExisteUnaPersonaConMismoDniException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();

    void save(Person newPerson) throws YaExisteUnaPersonaConMismoDniException;

    Person update(Person newPersona, String document);

    void delete(String document);

    Person getPersonByDocument(String document) throws DocumentoNoEncontradoException;
}
