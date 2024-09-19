package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ComplaintNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImageDto;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addImageToComplaint(@RequestBody ImageDto image) throws ComplaintNotFoundException {
        this.imageService.addImage(image);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{complaintID}")
    public ResponseEntity<Resource> findByComplaintID(@PathVariable String complaintID) throws MalformedURLException {
        Resource image = this.imageService.findByComplaintID(complaintID);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

}
