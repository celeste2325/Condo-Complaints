package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DuplicateDocumentException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "*")
public class PersonRestController {
    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> createPerson(@RequestBody Person person) throws DuplicateDocumentException {
        this.personService.createPerson(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{document}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable String document) {
        return new ResponseEntity<>(this.personService.update(person, document), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Person> findAll() {
        return this.personService.findAll();
    }

    @GetMapping("/{document}")
    public ResponseEntity<Person> findByDocument(@PathVariable String document) throws DocumentNotFoundException {
        return new ResponseEntity<>(this.personService.findByDocument(document), HttpStatus.OK);
    }

    @DeleteMapping("/{document}")
    public ResponseEntity<Void> deleteByID(@PathVariable String document) {
        this.personService.deleteByID(document);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
