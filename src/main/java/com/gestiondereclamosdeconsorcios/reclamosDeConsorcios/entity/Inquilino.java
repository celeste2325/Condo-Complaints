package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inquilinos", schema = "dbo", catalog = "gestion_reclamo_consorcio")
@PrimaryKeyJoinColumn(name = "documento")
public class Inquilino extends Persona {

    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "identificador")
    @EqualsAndHashCode.Include
    private Integer identificador;

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
