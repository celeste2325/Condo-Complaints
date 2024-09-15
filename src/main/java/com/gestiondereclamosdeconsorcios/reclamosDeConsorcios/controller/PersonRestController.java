package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Person;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "*")
public class PersonRestController {
    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public List<Person> getAll() {
        return this.personService.getAll();
    }

    @GetMapping("/{documentID}")
    public ResponseEntity personByDocument(@PathVariable String documentID) {
        try {
            return new ResponseEntity<>(this.personService.getPersonByDocument(documentID), HttpStatus.OK);
        } catch (DocumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity savePerson(@RequestBody Person newPerson) {
        try {
            this.personService.save(newPerson);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{documentID}")
    public ResponseEntity<Void> removePerson(@PathVariable String documentID) {
        this.personService.delete(documentID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{document}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person persona, @PathVariable String document) {
        return new ResponseEntity<>(this.personService.update(persona, document), HttpStatus.OK);
    }
}
