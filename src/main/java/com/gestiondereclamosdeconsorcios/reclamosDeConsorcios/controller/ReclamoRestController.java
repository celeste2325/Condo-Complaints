package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.SinReclamosCargadosException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamo;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ComplaintsByTenant;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamo")
@CrossOrigin(origins = "*")
public class ReclamoRestController {
    @Autowired
    ReclamoService reclamoService;

    @PostMapping("/")
    public ResponseEntity createNewReclamo(@RequestBody Reclamo newReclamo) {
        try {
            return new ResponseEntity<>(this.reclamoService.createReclamo(newReclamo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<Reclamo> getReclamos() {
        return this.reclamoService.getAll();
    }

    @GetMapping("estado/{estado}")
    public List<Reclamo> getReclamosPorEstado(@PathVariable String estado) {
        return this.reclamoService.getAllByEstado(estado);
    }

    @GetMapping("/getReclamos")
    public ResponseEntity getReclamos(@RequestParam(name = "codigoEdificio", defaultValue = "0") Integer codigoEdificio, @RequestParam(name = "codigoUnidad", defaultValue = "0") Integer codigoUnidad, @RequestParam(name = "idReclamo", defaultValue = "0") Integer idReclamo) {
        try {
            return new ResponseEntity<>(this.reclamoService.getReclamos(codigoEdificio, codigoUnidad, idReclamo), HttpStatus.OK);
        } catch (SinReclamosCargadosException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getReclamosByTenant/{tenantDocument}")
    public ResponseEntity<List<ComplaintsByTenant>> getComplaintsByTenant(@PathVariable String tenantDocument) {
        return new ResponseEntity<>(this.reclamoService.getComplaintsByTenant(tenantDocument), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEstadoReclamo(@RequestBody Reclamo reclamo, @PathVariable Integer id) {
        return new ResponseEntity(this.reclamoService.updateEstado(reclamo, id), HttpStatus.OK);
    }


}
