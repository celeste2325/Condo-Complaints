package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private static final String UPLOAD_DIR = "D:\\programacion2024\\Condo-Complaints\\src\\main\\java\\com\\gestiondereclamosdeconsorcios\\reclamosDeConsorcios\\";
    @Autowired
    ComplaintService complaintService;

    @PostMapping("/{complaintID}")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String complaintID) throws IOException {
        Complaint complaint = this.complaintService.getByID(complaintID);
        if (complaint != null) {
            String fileName = file.getOriginalFilename();
            String decodeFileName = java.net.URLDecoder.decode(fileName, StandardCharsets.UTF_8);
            Path claimDir = Paths.get(UPLOAD_DIR + "assets\\" + complaintID);
            if (!Files.exists(claimDir)) {
                Files.createDirectories(claimDir);
            }

            Path filePath = claimDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
            return ResponseEntity.ok("File uploads successfully: " + decodeFileName);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Complaint not found");
    }
}
