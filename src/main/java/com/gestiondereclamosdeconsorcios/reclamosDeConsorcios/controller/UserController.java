package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.UsuarioCreadoException;
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
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity guardarUsuario(@RequestBody Persona newPersonasLogin) {
        try {
            return new ResponseEntity<>(this.userDetailsService.saveUser(newPersonasLogin), HttpStatus.OK);
        } catch (UsuarioNoEsDuenioNiIquilinoDelEdificioException | UsuarioCreadoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity devolverUsuario(@RequestBody Persona newPersona) {
        try {
            return new ResponseEntity<>(this.userDetailsService.getUser(newPersona), HttpStatus.OK);
        } catch (UsuarioOContraseniaIncorrecta e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

