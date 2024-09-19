package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DuplicateDocumentException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    void createPerson(Person newPerson) throws DuplicateDocumentException;

    Person update(Person newPersona, String document);

    void deleteByID(String document);

    Person findByDocument(String document) throws DocumentNotFoundException;
}
