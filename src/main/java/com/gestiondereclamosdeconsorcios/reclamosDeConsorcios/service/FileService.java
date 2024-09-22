package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String handleFileUpload(MultipartFile file, String complaintID) throws IOException;
}
