package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentoNoEncontradoException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IdInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoCrearDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.InquilinoImpresionDto;
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
    public List<InquilinoImpresionDto> getInquilinos(){
        return this.inquilinoService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity getInquilinosID(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(inquilinoService.getById(id), HttpStatus.OK);
        }
        catch (IdInexistenteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getInquilinos/{documento}")
    public ResponseEntity getInquilinosDocumento(@PathVariable String documento) {
        try {
            return new ResponseEntity<>(this.inquilinoService.getByDocumento(documento), HttpStatus.OK);
        } catch (DocumentoNoEncontradoException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/")
    public ResponseEntity saveInquilino(@RequestBody InquilinoCrearDto newInquilino){
        try {
            this.inquilinoService.save(newInquilino);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInquilino(@PathVariable Integer id){
        this.inquilinoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{identificadorUnidad}/{codigoEdificio}")
    public ResponseEntity liberarUnidad(@PathVariable Integer identificadorUnidad,@PathVariable Integer codigoEdificio) throws UnidadInexistenteException {
        try {
            this.inquilinoService.liberarUnidad(identificadorUnidad, codigoEdificio);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }


}
