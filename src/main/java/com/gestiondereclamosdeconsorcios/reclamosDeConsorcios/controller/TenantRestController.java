package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdInexistenteException;
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
    public ResponseEntity createTenant(@RequestBody TenantDto tenant) {
        try {
            this.tenantService.createTenant(tenant);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<TenantResponseDto> findAll() {
        return this.tenantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findByID(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(tenantService.findByID(id), HttpStatus.OK);
        } catch (IdInexistenteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tenants/{document}")
    public ResponseEntity findByDocument(@PathVariable String document) {
        try {
            return new ResponseEntity<>(this.tenantService.findByDocument(document), HttpStatus.OK);
        } catch (DocumentoNoEncontradoException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{tenantID}")
    public ResponseEntity<Void> deleteByID(@PathVariable Integer tenantID) {
        this.tenantService.delete(tenantID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{unitID}/{buildingID}")
    public ResponseEntity<String> releaseUnit(@PathVariable Integer unitID, @PathVariable Integer buildingID) throws UnitNotFoundException {
        try {
            this.tenantService.releaseUnit(unitID, buildingID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
