package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "duenios", schema = "dbo", catalog = "gestion_reclamo_consorcio")
@PrimaryKeyJoinColumn(name = "documento")
public class Duenio extends Persona {
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
        if (!(o instanceof Duenio duenio)) return false;
        return Objects.equals(getIdentificador(), duenio.getIdentificador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentificador());
    }
}
