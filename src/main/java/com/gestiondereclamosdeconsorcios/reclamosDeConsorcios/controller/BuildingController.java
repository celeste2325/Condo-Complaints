package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

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
    public ResponseEntity createBuilding(@RequestBody Building building) {
        try {
            this.buildingService.createBuilding(building);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{buildingID}")
    ResponseEntity<Building> updateBuilding(@RequestBody Building newBuilding, @PathVariable Integer buildingID) {
        return new ResponseEntity<>(buildingService.updateBuilding(newBuilding, buildingID), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Building> findAll() {
        try {
            return this.buildingService.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @GetMapping("/{buildingID}")
    public Building findByID(@PathVariable Integer buildingID) {
        try {
            return this.buildingService.findByID(buildingID);
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    @GetMapping("/tenants/{buildingID}")
    public List<Tenant> findTenantsByBuildingID(@PathVariable Integer buildingID) {
        try {
            return this.buildingService.findTenantsByBuildingID(buildingID);
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    @GetMapping("/buildings/tenant/{tenantDocumentID}")
    public ResponseEntity<List<BuildingWithUnitsByTenant>> findByTenant(@PathVariable String tenantDocumentID) {
        return new ResponseEntity<>(this.buildingService.findByTenant(tenantDocumentID), HttpStatus.OK);
    }

    @GetMapping("/habilitados/{buildingID}")
    public List<Tenant> getHabilitadosEdificio(@PathVariable Integer buildingID) {
        return this.buildingService.getHabilitados(buildingID);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteByID(@PathVariable Integer id) {
        buildingService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
