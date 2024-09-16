package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.CondoOwnerNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.IncorrectDocumentOrPasswordException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.existingAccount;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.AuthDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthServiceImpl userDetailsService;

    public AuthController(AuthServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity signUp(@RequestBody AuthDto singUpData) {
        try {
            return new ResponseEntity<>(this.userDetailsService.signUp(singUpData), HttpStatus.OK);
        } catch (CondoOwnerNotFoundException | existingAccount e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody AuthDto loginData) {
        try {
            return new ResponseEntity<>(this.userDetailsService.logIn(loginData), HttpStatus.OK);
        } catch (IncorrectDocumentOrPasswordException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

