package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unidad;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnidadDto;
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
    @GetMapping("/getUnidad/{identificador}")
    public ResponseEntity getUnidadesID(@PathVariable Integer identificador)  {
        try {
            return new ResponseEntity<>(unidadService.getID(identificador), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/")
    public ResponseEntity CrearUnidad(@RequestBody UnidadDto newUnidad) {
        try {

            return new ResponseEntity<>(unidadService.saveUnidad(newUnidad),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidad> replaceUnidad(@RequestBody Unidad newUnidad, @PathVariable Integer id) {
        return new ResponseEntity<>(unidadService.update(newUnidad, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUnidad(@PathVariable Integer id) {
        try {
            unidadService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
