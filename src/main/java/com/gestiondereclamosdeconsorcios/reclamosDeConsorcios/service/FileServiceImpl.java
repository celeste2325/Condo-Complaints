package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Complaint;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    private static final String UPLOAD_DIR = "src/main/java/com/gestiondereclamosdeconsorcios/reclamosDeConsorcios/";
    private final ComplaintService complaintService;

    public FileServiceImpl(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }


    @Override
    public String handleFileUpload(MultipartFile file, String complaintID) throws IOException {
        Complaint complaint = this.complaintService.getByID(complaintID);
        String decodeFileName = "";
        if (complaint != null) {
            String fileName = file.getOriginalFilename();
            decodeFileName = java.net.URLDecoder.decode(fileName, StandardCharsets.UTF_8);
            Path claimDir = Paths.get(UPLOAD_DIR + "assets\\" + complaintID);
            if (!Files.exists(claimDir)) {
                Files.createDirectories(claimDir);
            }

            Path filePath = claimDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
        }
        return decodeFileName;
    }
}
