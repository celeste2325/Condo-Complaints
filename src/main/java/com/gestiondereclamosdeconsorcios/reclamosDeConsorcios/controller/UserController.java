package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioNoEsDuenioNiIquilinoDelEdificioException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioOContraseniaIncorrecta;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userDetailsService;

    @PostMapping("/")
    public ResponseEntity guardarUsuario(@RequestBody Persona newPersonaLogin) {
    try {
        return new ResponseEntity<>(this.userDetailsService.saveUser(newPersonaLogin), HttpStatus.BAD_REQUEST);
    } catch (UsuarioNoEsDuenioNiIquilinoDelEdificioException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping("/{documento}/{contrasenia}")
    public ResponseEntity devolverUsuario(@PathVariable String documento, @PathVariable String contrasenia) {
        try {
            return new ResponseEntity<>(this.userDetailsService.getUser(documento,contrasenia), HttpStatus.OK);
        } catch (UsuarioOContraseniaIncorrecta e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
