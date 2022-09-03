package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.EdificioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edificio")
public class EdificioRestController {
    private EdificioServiceImpl edificioServiceImpl;

    public EdificioRestController(EdificioServiceImpl edificioServiceImpl) {
        this.edificioServiceImpl = edificioServiceImpl;
    }

    @PostMapping("/")
        ResponseEntity crearEdificio(@RequestBody Edificio newEdificio){
            try {
                this.edificioServiceImpl.saveEdificio(newEdificio);
                return new ResponseEntity(HttpStatus.CREATED);
            } catch(Exception e) {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @GetMapping("/")
    public List<Edificio> getEdificios(){

        List<Edificio> edificios =  this.edificioServiceImpl.getAll();
        return edificios;
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Integer id){
        edificioServiceImpl.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    ResponseEntity<Edificio> replaceEdificio(@RequestBody Edificio newEdificio, @PathVariable Integer id){
        return new ResponseEntity<>(edificioServiceImpl.update(newEdificio, id), HttpStatus.OK);
    }


}
