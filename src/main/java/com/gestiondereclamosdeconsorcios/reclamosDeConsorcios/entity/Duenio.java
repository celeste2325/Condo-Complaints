package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "duenios", schema = "dbo", catalog = "gestion_reclamo_consorcio")
@PrimaryKeyJoinColumn(name = "documento")
public class Duenio extends Persona {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "identificador", updatable = false, insertable = false)
    private Integer identificador;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador")
    @JsonBackReference(value = "unidad-duenio")
    private Unidad identificadorDuenio;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Unidad getIdentificadorDuenio() {
        return identificadorDuenio;
    }

    public void setIdentificadorDuenio(Unidad identificadorDuenio) {
        this.identificadorDuenio = identificadorDuenio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Duenio duenio)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), duenio.getId()) && Objects.equals(getIdentificador(), duenio.getIdentificador()) && Objects.equals(getIdentificadorDuenio(), duenio.getIdentificadorDuenio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getIdentificador(), getIdentificadorDuenio());
    }
}
