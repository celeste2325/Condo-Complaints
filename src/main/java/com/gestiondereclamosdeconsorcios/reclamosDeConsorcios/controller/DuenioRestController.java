package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.DuenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.List;

@RestController
@RequestMapping("/api/duenio")
public class DuenioRestController {
    @Autowired
    DuenioService duenioService;

    @GetMapping("/")
    public List<Duenio> getDuenios() {
        return duenioService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity crearDueño(@RequestBody Duenio newDuenio){
        System.out.println(new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, new SecureRandom("cele".getBytes())));
        try {
            duenioService.saveDuenio(newDuenio);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDuenio(@RequestBody Duenio newDuenio, @PathVariable Integer id){
        return new ResponseEntity<>(duenioService.update(newDuenio, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDuenio(@PathVariable Integer id) {
        duenioService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
