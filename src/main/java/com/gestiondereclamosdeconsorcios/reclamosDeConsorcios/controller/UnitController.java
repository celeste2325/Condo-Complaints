package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ExistingUnitException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Unit;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.CreationUnitDto;
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
    public ResponseEntity<Integer> createUnit(@RequestBody CreationUnitDto newUnit) throws BuildingNotFoundException, ExistingUnitException {
        return new ResponseEntity<>(unitService.createUnit(newUnit), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unit> updateUnit(@RequestBody Unit newUnit, @PathVariable Integer id) {
        return new ResponseEntity<>(unitService.updateUnit(newUnit, id), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Unit> findAll() {
        return this.unitService.findAll();
    }

    @GetMapping("/{unitID}")
    public ResponseEntity<Unit> findByID(@PathVariable Integer unitID) throws UnitNotFoundException {
        return new ResponseEntity<>(unitService.findByID(unitID), HttpStatus.OK);
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
