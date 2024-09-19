package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DuplicateDocumentException;
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
    public void createPerson(Person newPerson) throws DuplicateDocumentException {
        boolean personExists = this.personRepository.existsById(newPerson.getDocument());
        if (!personExists) {
            this.personRepository.save(newPerson);
        } else {
            throw new DuplicateDocumentException("A person with the same document already exists.");
        }
    }

    @Override
    public Person update(Person newPerson, String document) {
        return this.personRepository.findById(document).map(person -> {
            person.setName(newPerson.getName());
            return this.personRepository.save(person);
        }).get();
    }

    @Override
    public void deleteByID(String document) {
        this.personRepository.deleteById(document);
    }

    @Override
    public Person findByDocument(String documentID) throws DocumentNotFoundException {
        Optional<Person> persona = this.personRepository.findByDocument(documentID);
        if (persona.isPresent()) {
            return persona.get();
        } else {
            throw new DocumentNotFoundException("The document is not found in the condo data.");
        }
    }


}
