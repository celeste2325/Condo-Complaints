package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.BuildingNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Building;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Tenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.BuildingWithUnitsByTenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
@CrossOrigin(origins = "*")
public class BuildingController {
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createBuilding(@RequestBody Building building) {
        try {
            this.buildingService.createBuilding(building);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{buildingID}")
    ResponseEntity<Building> updateBuilding(@RequestBody Building newBuilding, @PathVariable Integer buildingID) {
        return new ResponseEntity<>(buildingService.updateBuilding(newBuilding, buildingID), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Building> findAll() {
        return this.buildingService.findAll();
    }

    @GetMapping("/{buildingID}")
    public ResponseEntity<Object> findByID(@PathVariable Integer buildingID) {
        try {
            return new ResponseEntity<>(this.buildingService.findByID(buildingID), HttpStatus.OK);
        } catch (BuildingNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tenants/{buildingID}")
    public ResponseEntity<List<Tenant>> findTenantsByBuildingID(@PathVariable Integer buildingID) {
        return new ResponseEntity<>(this.buildingService.findTenantsByBuildingID(buildingID), HttpStatus.OK);
    }

    @GetMapping("/buildings/tenant/{tenantDocumentID}")
    public ResponseEntity<List<BuildingWithUnitsByTenant>> findByTenant(@PathVariable String tenantDocumentID) {
        return new ResponseEntity<>(this.buildingService.findByTenant(tenantDocumentID), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteByID(@PathVariable Integer id) {
        buildingService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
