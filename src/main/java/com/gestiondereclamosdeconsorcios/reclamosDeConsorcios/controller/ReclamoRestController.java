package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Reclamos;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamo")
public class ReclamoRestController {
    @Autowired
    ReclamoService reclamoService;
    @PostMapping("/")
    public ResponseEntity createNewReclamo(@RequestBody Reclamos newReclamo) {
        try {
            this.reclamoService.createReclamo(newReclamo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<Reclamos> getReclamos(){
        return this.reclamoService.getAll();
    }

    @GetMapping("estado/{estado}")
    public List<Reclamos> getReclamosPorEstado(@PathVariable String estado){
        return this.reclamoService.getAllByEstado(estado);
    }

    @GetMapping("/{id}")
    public Optional<Reclamos> getReclamosPorEstado(@PathVariable Integer id){
        return this.reclamoService.getById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity updateEstadoReclamo(@RequestBody Reclamos reclamo, @PathVariable Integer id){
        return new ResponseEntity(this.reclamoService.updateEstado(reclamo, id), HttpStatus.OK);
    }
}
