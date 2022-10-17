package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona")
public class PersonaRestController {
    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public List<Persona> getAll() {
        return this.personaService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity savePersona(@RequestBody Persona newPersona) {
        try {
            this.personaService.save(newPersona);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{documento}")
    public ResponseEntity<Void> removePersona(@PathVariable String documento) {
        this.personaService.delete(documento);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{documento}")
    public ResponseEntity<Persona> updatePersona(@RequestBody Persona persona, @PathVariable String documento) {
        return new ResponseEntity<>(this.personaService.update(persona, documento), HttpStatus.OK);
    }
}
