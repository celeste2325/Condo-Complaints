package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Imagen;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ImagenDto {
    List<Imagen> imagenes;
    private Integer idReclamo;

    public ImagenDto() {
        this.imagenes = new ArrayList<>();
    }
}
