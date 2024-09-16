package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.NoSeEncontraronDueniosException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.OwnerDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.OwnerResponseDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "*")
public class OwnerRestController {
    private final OwnerService ownerService;

    public OwnerRestController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/")
    public ResponseEntity createOwner(@RequestBody OwnerDto ownerDto) {
        try {
            this.ownerService.createOwner(ownerDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<OwnerResponseDto> findAll() {
        return ownerService.findAll();
    }

    @GetMapping("/owners/{buildingID}")
    public ResponseEntity findByBuildingID(@PathVariable Integer buildingID) {
        try {
            return new ResponseEntity<>(ownerService.dueniosPorEdificio(buildingID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/owners")
    public ResponseEntity findByParameter(@RequestParam(name = "unitID", defaultValue = "0") Integer unitID,
                                          @RequestParam(name = "id", defaultValue = "0") Integer id,
                                          @RequestParam(name = "document", defaultValue = "0") String document) {
        try {
            return new ResponseEntity<>(this.ownerService.findByParameter(unitID, id, document), HttpStatus.OK);
        } catch (NoSeEncontraronDueniosException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{documentoNvo}/{id}/{documentoDuenioAntiguo}")
    public ResponseEntity transferirDuenio(@PathVariable String documentoNvo, @PathVariable Integer id, @PathVariable String documentoDuenioAntiguo) throws NoSeEncontraronDueniosException {
        try {
            ownerService.update(documentoNvo, id, documentoDuenioAntiguo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable Integer id) {
        try {
            ownerService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
