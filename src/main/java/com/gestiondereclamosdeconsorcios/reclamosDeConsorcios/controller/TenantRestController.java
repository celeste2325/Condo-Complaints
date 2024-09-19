package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.OwnerAlreadyAssignedToUnitException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.TenantDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.TenantResponseDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantRestController {
    private final TenantService tenantService;

    public TenantRestController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> assignTenantToUnit(@RequestBody TenantDto tenant) throws DocumentNotFoundException, UnitNotFoundException, OwnerAlreadyAssignedToUnitException {
        this.tenantService.assignTenantToUnit(tenant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<TenantResponseDto> findAll() {
        return this.tenantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponseDto> findByID(@PathVariable Integer id) throws IdNotFoundException {
        return new ResponseEntity<>(tenantService.findByID(id), HttpStatus.OK);
    }

    @GetMapping("/tenants/{document}")
    public ResponseEntity<List<TenantResponseDto>> findByDocument(@PathVariable String document) throws DocumentNotFoundException {
        return new ResponseEntity<>(this.tenantService.findByDocument(document), HttpStatus.OK);
    }

    @DeleteMapping("/{tenantID}")
    public ResponseEntity<Void> deleteByID(@PathVariable Integer tenantID) {
        this.tenantService.delete(tenantID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{unitID}/{buildingID}")
    public ResponseEntity<Void> releaseUnit(@PathVariable Integer unitID, @PathVariable Integer buildingID) throws UnitNotFoundException {
        this.tenantService.releaseUnit(unitID, buildingID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
