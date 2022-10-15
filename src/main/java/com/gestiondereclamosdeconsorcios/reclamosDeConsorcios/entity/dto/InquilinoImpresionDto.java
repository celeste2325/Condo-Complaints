package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Inquilino;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InquilinoImpresionDto {
    private Integer id;
    private String documento;
    private String nombre;
    private Integer codigoUnidad;

    public InquilinoImpresionDto(Inquilino inquilino) {
        this.id = inquilino.getId();
        this.documento = inquilino.getDocumento();
        this.nombre = inquilino.getPersona().getNombre();
        this.codigoUnidad = inquilino.getIdentificador();
    }
}
