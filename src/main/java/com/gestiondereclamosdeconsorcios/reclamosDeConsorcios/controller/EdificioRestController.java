package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificios;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.EdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edificio")
public class EdificioRestController {
    @Autowired
    private EdificioService edificioService;

    @PostMapping("/")
    public ResponseEntity crearEdificio(@RequestBody Edificios newEdificios) {
        try {
            this.edificioService.saveEdificio(newEdificios);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<Edificios> getEdificios() {
        List<Edificios> edificios = this.edificioService.getAll();
        return edificios;
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteEdificio(@PathVariable Integer id) {
        edificioService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Edificios> replaceEdificio(@RequestBody Edificios newEdificios, @PathVariable Integer id) {
        return new ResponseEntity<>(edificioService.update(newEdificios, id), HttpStatus.OK);
    }


}
