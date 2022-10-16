package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.Exceptions.ReclamoInexistenteException;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto.ImagenDto;

public interface ImagenService {
   void agregarImagen(ImagenDto Imagen) throws ReclamoInexistenteException;
}
