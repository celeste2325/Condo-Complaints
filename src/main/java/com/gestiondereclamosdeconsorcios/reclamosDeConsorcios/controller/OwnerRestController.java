package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.DocumentNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.OwnerAlreadyAssignedToUnitException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.OwnerNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UnitNotFoundException;
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
    public ResponseEntity<Void> assignOwnerToUnit(@RequestBody OwnerDto ownerDto) throws DocumentNotFoundException, UnitNotFoundException, OwnerAlreadyAssignedToUnitException {
        this.ownerService.assignOwnerToUnit(ownerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<OwnerResponseDto> findAll() {
        return ownerService.findAll();
    }

    @GetMapping("/owners/{buildingID}")
    public ResponseEntity<Object> findByBuildingID(@PathVariable Integer buildingID) {
        try {
            return new ResponseEntity<>(ownerService.findByBuildingID(buildingID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/owners")
    public ResponseEntity<Object> findByParameter(@RequestParam(name = "unitID", defaultValue = "0") Integer unitID,
                                                  @RequestParam(name = "id", defaultValue = "0") Integer id,
                                                  @RequestParam(name = "document", defaultValue = "0") String document) {
        try {
            return new ResponseEntity<>(this.ownerService.findByParameter(unitID, id, document), HttpStatus.OK);
        } catch (OwnerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{newOwnerDocument}/{unitID}/{previousOwnerDocument}")
    public ResponseEntity<String> assignOwnerToUnit(@PathVariable String newOwnerDocument, @PathVariable Integer unitID, @PathVariable String previousOwnerDocument) throws OwnerNotFoundException {
        try {
            ownerService.assignOwnerToUnit(newOwnerDocument, unitID, previousOwnerDocument);
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
