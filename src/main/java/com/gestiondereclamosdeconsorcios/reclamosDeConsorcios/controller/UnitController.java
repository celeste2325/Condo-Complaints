package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnidadDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unit")
@CrossOrigin(origins = "*")
public class UnitController {
    @Autowired
    UnitService unitService;

    @GetMapping("/")
    public List<Unit> getUnits() {
        List<Unit> units = unitService.getAll();
        return units;
    }

    @GetMapping("/{unitID}")
    public ResponseEntity getUnitByUnitID(@PathVariable Integer unitID) {
        try {
            return new ResponseEntity<>(unitService.getID(unitID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/")
    public ResponseEntity createUnit(@RequestBody UnidadDto newUnit) {
        try {
            return new ResponseEntity<>(unitService.saveUnit(newUnit), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unit> replaceUnidad(@RequestBody Unit newUnit, @PathVariable Integer id) {
        return new ResponseEntity<>(unitService.update(newUnit, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUnit(@PathVariable Integer id) {
        try {
            unitService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
