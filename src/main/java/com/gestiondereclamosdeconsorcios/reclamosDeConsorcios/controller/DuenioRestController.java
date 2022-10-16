package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.DuenioCrearDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.DuenioImpresionDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.DuenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duenio")
public class DuenioRestController {
    @Autowired
    DuenioService duenioService;

    @GetMapping("/")
    public List<DuenioImpresionDto> getDuenios() {
        return duenioService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity getDuenioDocumento(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(duenioService.getById(id), HttpStatus.OK);
        }
        catch (IdInexistenteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDuenios/{documento}")
    public ResponseEntity getDueniosDocumento(@PathVariable String documento) {
        try {
            return new ResponseEntity<>(duenioService.getByDocumento(documento), HttpStatus.OK);
        } catch (DocumentoNoEncontradoException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity crearDueño(@RequestBody DuenioCrearDto newDuenio) {
        try {
            this.duenioService.saveDuenio(newDuenio);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDuenio(@PathVariable Integer id) {
        try {
            duenioService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
