package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidad")
public class UnidadRestController {
    @Autowired
    UnidadService unidadService;
    @GetMapping("/")
    public List<Unidad> getUnidades() {
        List<Unidad> unidades = unidadService.getAll();
        return unidades;
    }
    @PostMapping("/")
    public ResponseEntity CrearUnidad(@RequestBody Unidad newUnidad) {
        try {
            unidadService.saveUnidad(newUnidad);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidad> replaceUnidad(@RequestBody Unidad newUnidad, @PathVariable Integer id) {
        return new ResponseEntity<>(unidadService.update(newUnidad, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Unidad> deleteUnidad(@PathVariable Integer id) {
        unidadService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
