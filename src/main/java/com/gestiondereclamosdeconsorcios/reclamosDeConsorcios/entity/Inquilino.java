package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inquilinos", schema = "dbo", catalog = "gestion_reclamo_consorcio")
@PrimaryKeyJoinColumn(name = "documento")
public class Inquilino extends Persona {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "identificador", updatable = false, insertable = false)
    private Integer identificador;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador")
    @JsonBackReference(value = "unidad-inquilino")
    private Unidad identificadorInquilino;

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Unidad getIdentificadorInquilino() {
        return identificadorInquilino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdentificadorInquilino(Unidad unidadByIdentificador_inquilino) {
        this.identificadorInquilino = unidadByIdentificador_inquilino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inquilino inquilino)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), inquilino.getId()) && Objects.equals(getIdentificador(), inquilino.getIdentificador()) && Objects.equals(getIdentificadorInquilino(), inquilino.getIdentificadorInquilino());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getIdentificador(), getIdentificadorInquilino());
    }
}
