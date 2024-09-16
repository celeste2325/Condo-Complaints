package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.UnidadDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unit")
@CrossOrigin(origins = "*")
public class UnitController {
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping("/")
    public ResponseEntity createUnit(@RequestBody UnidadDto newUnit) {
        try {
            return new ResponseEntity<>(unitService.createUnit(newUnit), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unit> updateUnit(@RequestBody Unit newUnit, @PathVariable Integer id) {
        return new ResponseEntity<>(unitService.updateUnit(newUnit, id), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Unit> findAll() {
        List<Unit> units = unitService.findAll();
        return units;
    }

    @GetMapping("/{unitID}")
    public ResponseEntity findByID(@PathVariable Integer unitID) {
        try {
            return new ResponseEntity<>(unitService.findByID(unitID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{unitID}")
    public ResponseEntity<String> deleteByID(@PathVariable Integer unitID) {
        try {
            unitService.deleteUnit(unitID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
