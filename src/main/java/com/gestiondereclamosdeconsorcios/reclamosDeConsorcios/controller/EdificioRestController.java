package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.BuildingWithUnitsByTenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Edificio;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.EdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edificio")
@CrossOrigin(origins = "*")
public class EdificioRestController {
    @Autowired
    private EdificioService edificioService;

    @PostMapping("/")
    public ResponseEntity crearEdificio(@RequestBody Edificio newEdificio) {
        try {
            this.edificioService.saveEdificio(newEdificio);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<Edificio> getEdificios() {
        try {
            return this.edificioService.getAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @GetMapping("/habitantes/{codigo}")
    public List<Inquilino> getHabitantesEdificio(@PathVariable Integer codigo) {
        try {
            return this.edificioService.getHabitantes(codigo);
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    @GetMapping("/{codigoEdificio}")
    public Edificio getEdificioByCodigo(@PathVariable Integer codigoEdificio) {
        try {
            return this.edificioService.getEdificio(codigoEdificio);
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    @GetMapping("/habilitados/{codigo}")
    public List<Inquilino> getHabilitadosEdificio(@PathVariable Integer codigo) {
        return this.edificioService.getHabilitados(codigo);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteEdificio(@PathVariable Integer id) {
        edificioService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Edificio> replaceEdificio(@RequestBody Edificio newEdificio, @PathVariable Integer id) {
        return new ResponseEntity<>(edificioService.update(newEdificio, id), HttpStatus.OK);
    }

    @GetMapping("/getBuildingWithUnits/{tenantDocumentID}")
    public List<BuildingWithUnitsByTenant> getBuildingByTenant(@PathVariable String tenantDocumentID) {
        return this.edificioService.getBuildingByTenant(tenantDocumentID);
    }

}
