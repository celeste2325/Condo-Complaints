package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.controller;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/{complaintID}")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String complaintID) throws IOException {
        return ResponseEntity.ok("File uploads successfully: " + this.fileService.handleFileUpload(file, complaintID));
    }
}
