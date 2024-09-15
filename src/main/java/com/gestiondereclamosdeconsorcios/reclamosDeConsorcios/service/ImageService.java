package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ReclamoInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Image;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImagenDto;

public interface ImageService {
    void addImage(ImagenDto imageDto) throws ReclamoInexistenteException;

    Image getImageByComplaintID(String complaintID);
}
