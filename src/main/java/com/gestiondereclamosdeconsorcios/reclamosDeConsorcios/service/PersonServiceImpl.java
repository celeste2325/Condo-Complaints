package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.YaExisteUnaPersonaConMismoDniException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    @Override
    public void createPerson(Person newPerson) throws YaExisteUnaPersonaConMismoDniException {
        boolean existeLaPersona = this.personRepository.existsById(newPerson.getDocument());
        if (!existeLaPersona) {
            this.personRepository.save(newPerson);
        } else {
            throw new YaExisteUnaPersonaConMismoDniException("ya existe una persona con mismo documento");
        }
    }

    @Override
    public Person update(Person newPersona, String documento) {
        return this.personRepository.findById(documento).map(persona -> {
            persona.setName(newPersona.getName());
            return this.personRepository.save(persona);
        }).get();
    }

    @Override
    public void deleteByID(String document) {
        this.personRepository.deleteById(document);
    }

    @Override
    public Person findByDocument(String documentID) throws DocumentoNoEncontradoException {
        Optional<Person> persona = this.personRepository.findByDocument(documentID);
        if (persona.isPresent()) {
            return persona.get();
        } else {
            throw new DocumentoNoEncontradoException("The document ID must belong to a condo owner/tenant");
        }
    }


}
