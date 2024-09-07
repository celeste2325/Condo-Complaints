package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ReclamoInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Imagen;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImagenDto;

public interface ImageService {
    void agregarImagen(ImagenDto Imagen) throws ReclamoInexistenteException;

    Imagen getImageByComplaintID(String complaintID);
}
