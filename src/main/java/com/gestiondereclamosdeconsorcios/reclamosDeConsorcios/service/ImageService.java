package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ComplaintNotFoundException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImageDto;
import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

public interface ImageService {
    void addImage(ImageDto imageDto) throws ComplaintNotFoundException;

    Resource findByComplaintID(String complaintID) throws MalformedURLException;
}
