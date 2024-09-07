package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Imagen;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImagenDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageController {
    private static final String UPLOAD_DIR = "D:\\programacion2024\\Condo-Complaints\\src\\main\\java\\com\\gestiondereclamosdeconsorcios\\reclamosDeConsorcios\\";
    @Autowired
    ImageService imagenService;

    @PostMapping("/")
    public ResponseEntity cargarImagenesAReclamo(@RequestBody ImagenDto imagen) {
        try {
            this.imagenService.agregarImagen(imagen);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{complaintID}")
    public ResponseEntity<Resource> getImageByComplaintID(@PathVariable String complaintID) throws MalformedURLException {
        Imagen imageByComplaintID = this.imagenService.getImageByComplaintID(complaintID);

        Path imagePath = Paths.get(UPLOAD_DIR + imageByComplaintID.getDataFoto() + "." + imageByComplaintID.getTipo());
        Resource image = new UrlResource(imagePath.toUri());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

}
