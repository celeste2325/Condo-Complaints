package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inquilinos", schema = "dbo", catalog = "gestion_reclamo_consorcio")
@PrimaryKeyJoinColumn(name = "documento")
public class Inquilino extends Persona {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "identificador")
    private Integer identificador;

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inquilino inquilino)) return false;
        return Objects.equals(getIdentificador(), inquilino.getIdentificador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentificador());
    }
}
