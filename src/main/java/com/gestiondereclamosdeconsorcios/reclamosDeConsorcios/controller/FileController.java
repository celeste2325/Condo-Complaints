package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class FileController {
    private static final String UPLOAD_DIR = "D:\\programacion2024\\Condo-Complaints\\src\\main\\java\\com\\gestiondereclamosdeconsorcios\\reclamosDeConsorcios\\assets\\";

    @PostMapping("/{complaintID}")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String complaintID) throws IOException {
        String fileName = file.getOriginalFilename();
        String decodeFileName = java.net.URLDecoder.decode(fileName, StandardCharsets.UTF_8);
        Path claimDir = Paths.get(UPLOAD_DIR + complaintID);
        if (!Files.exists(claimDir)) {
            Files.createDirectories(claimDir);
        }

        Path filePath = claimDir.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        return ResponseEntity.ok("File uploads successfully: " + decodeFileName);
    }
}
