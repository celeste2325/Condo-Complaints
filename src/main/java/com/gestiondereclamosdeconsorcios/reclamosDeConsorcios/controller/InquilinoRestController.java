package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
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
    public List<Inquilino> getAll(){
        return this.inquilinoService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<Void> saveInquilino(@RequestBody Inquilino newInquilino){
        try {
            this.inquilinoService.save(newInquilino);
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
    public ResponseEntity<Inquilino> updateInquilino(@RequestBody Inquilino newInquilino, @PathVariable Integer id){
        return new ResponseEntity<>(this.inquilinoService.update(newInquilino,id), HttpStatus.OK);
    }

}
