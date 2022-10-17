package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoSeEncontraronDueniosException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnidadInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
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

    @GetMapping("/getDueniosPorEdificio/{codigo}")
    public ResponseEntity getDueniosDocumento(@PathVariable Integer codigo) {
        try {
            return new ResponseEntity<>(duenioService.dueniosPorEdificio(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDueniosRequest/")
    public ResponseEntity getDueniosDocumento(@RequestParam(name = "codigoUnidad", defaultValue = "0") Integer codigoUnidad,
                                              @RequestParam(name = "idDuenio", defaultValue = "0") Integer idDuenio,
                                              @RequestParam(name = "documento", defaultValue = "0") String documento) {
        try {
            return new ResponseEntity<>(this.duenioService.getDuenios(codigoUnidad, idDuenio, documento), HttpStatus.OK);
        } catch (NoSeEncontraronDueniosException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/")
    public ResponseEntity crearDueño(@RequestBody DuenioCrearDto newDuenio) {
        try {
            this.duenioService.saveDuenio(newDuenio);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{documentoNvo}/{id}/{documentoDuenioAntiguo}")
    public ResponseEntity transferirDuenio(@PathVariable String documentoNvo, @PathVariable Integer id, @PathVariable String documentoDuenioAntiguo) throws NoSeEncontraronDueniosException {
        try {
            duenioService.update(documentoNvo, id, documentoDuenioAntiguo);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
        }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteDuenio(@PathVariable Integer id) {
        try {
            duenioService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
