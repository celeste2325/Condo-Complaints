package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.UnidadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidad")
public class UnidadRestController {
    @Autowired
    UnidadServiceImpl unidadServiceImpl;

    public UnidadRestController(UnidadServiceImpl unidadServiceImpl) {
        this.unidadServiceImpl = unidadServiceImpl;
    }

    @GetMapping("/")
    public List<Unidad> getUnidades() {
        List<Unidad> unidades = unidadServiceImpl.getAll();
        return unidades;
    }

    @PostMapping("/")
    public ResponseEntity CrearUnidad(@RequestBody Unidad newUnidad) {
        try {
            unidadServiceImpl.saveUnidad(newUnidad);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidad> replaceUnidad(@RequestBody Unidad newUnidad, @PathVariable Integer id) {
        return new ResponseEntity<>(unidadServiceImpl.update(newUnidad, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Unidad> deleteUnidad(@PathVariable Integer id) {
        unidadServiceImpl.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
