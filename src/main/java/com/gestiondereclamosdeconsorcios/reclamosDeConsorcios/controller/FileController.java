package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class FileController {
    private static final String UPLOAD_DIR = "D:\\programacion2024\\Condo-Complaints\\src\\main\\java\\com\\gestiondereclamosdeconsorcios\\reclamosDeConsorcios\\assets\\";

    @PostMapping("/")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String decodeFileName = java.net.URLDecoder.decode(file.getOriginalFilename(), "UTF-8");
            Path filePath = Paths.get(UPLOAD_DIR + decodeFileName);
            Files.copy(file.getInputStream(), filePath);
            return ResponseEntity.ok("File uploads successfully: " + decodeFileName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}
