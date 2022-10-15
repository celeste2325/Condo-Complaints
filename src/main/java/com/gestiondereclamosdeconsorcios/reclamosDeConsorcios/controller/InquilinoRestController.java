package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilinos;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.InquilinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquilino")
public class InquilinoRestController {
    @Autowired
    InquilinoService inquilinoService;

    @GetMapping("/")
    public List<Inquilinos> getAll(){
        return this.inquilinoService.getAll();
    }

    @GetMapping("/{documento}")
    public List<Inquilinos> getAllByDocument(@PathVariable String documento){
        return this.inquilinoService.getAllByDocument(documento);
    }

    @PostMapping("/")
    public ResponseEntity<Void> saveInquilino(@RequestBody Inquilinos newInquilinos){
        try {
            this.inquilinoService.save(newInquilinos);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInquilino(@PathVariable Integer id){
        this.inquilinoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inquilinos> updateInquilino(@RequestBody Inquilinos newInquilinos, @PathVariable Integer id){
        return new ResponseEntity<>(this.inquilinoService.update(newInquilinos,id), HttpStatus.OK);
    }

}
