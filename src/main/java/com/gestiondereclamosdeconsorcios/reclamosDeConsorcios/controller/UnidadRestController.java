package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidades;
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
    public List<Unidades> getUnidades() {
        List<Unidades> unidades = unidadService.getAll();
        return unidades;
    }
    @PostMapping("/")
    public ResponseEntity CrearUnidad(@RequestBody Unidades newUnidad) {
        try {
            unidadService.saveUnidad(newUnidad);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidades> replaceUnidad(@RequestBody Unidades newUnidad, @PathVariable Integer id) {
        return new ResponseEntity<>(unidadService.update(newUnidad, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Unidades> deleteUnidad(@PathVariable Integer id) {
        unidadService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
