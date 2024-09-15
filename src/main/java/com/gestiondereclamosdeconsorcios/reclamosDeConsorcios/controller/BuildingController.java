package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Building;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.BuildingWithUnitsByTenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
@CrossOrigin(origins = "*")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @PostMapping("/")
    public ResponseEntity createBuilding(@RequestBody Building newBuilding) {
        try {
            this.buildingService.createBuilding(newBuilding);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<Building> getBuildings() {
        try {
            return this.buildingService.getAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @GetMapping("/habitantes/{buildingID}")
    public List<Inquilino> getHabitantesEdificio(@PathVariable Integer buildingID) {
        try {
            return this.buildingService.getHabitantes(buildingID);
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    @GetMapping("/{buildingID}")
    public Building getBuildingByID(@PathVariable Integer buildingID) {
        try {
            return this.buildingService.getBuilding(buildingID);
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    @GetMapping("/habilitados/{codigo}")
    public List<Inquilino> getHabilitadosEdificio(@PathVariable Integer codigo) {
        return this.buildingService.getHabilitados(codigo);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteBuilding(@PathVariable Integer id) {
        buildingService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Building> replaceEdificio(@RequestBody Building newBuilding, @PathVariable Integer id) {
        return new ResponseEntity<>(buildingService.update(newBuilding, id), HttpStatus.OK);
    }

    @GetMapping("/getBuildingWithUnits/{tenantDocumentID}")
    public List<BuildingWithUnitsByTenant> getBuildingByTenant(@PathVariable String tenantDocumentID) {
        return this.buildingService.getBuildingByTenant(tenantDocumentID);
    }

}
