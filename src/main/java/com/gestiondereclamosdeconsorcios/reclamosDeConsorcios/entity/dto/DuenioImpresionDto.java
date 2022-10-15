package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Duenio;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DuenioImpresionDto {
    private Integer id;
    private String documento;
    private String nombre;
    private Integer codigoUnidad;

    public DuenioImpresionDto(Duenio duenio) {
        this.id = duenio.getId();
        this.documento = duenio.getDocumento();
        this.nombre = duenio.getPersona().getNombre();
        this.codigoUnidad = duenio.getIdentificador();
    }
}
