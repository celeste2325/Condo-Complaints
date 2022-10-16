package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImagenDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/imagen")
public class ImagenRestController {
    @Autowired
    ImagenService imagenService;

    @PostMapping("/")
    public ResponseEntity cargarImagenesAReclamo(@RequestBody ImagenDto imagen) {
        try {
            this.imagenService.agregarImagen(imagen);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
