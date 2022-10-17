package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inquilinos", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Inquilino {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "documento", updatable = false, insertable = false)
    private String documento;

    @Basic
    @Column(name = "identificador", updatable = false, insertable = false)
    private Integer identificador;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador")
    @JsonBackReference(value = "unidad-inquilino")
    private Unidad identificadorInquilino;

    @ManyToOne
    @JoinColumn(name = "documento", referencedColumnName = "documento")
    @JsonBackReference(value = "persona-inquilino")
    private Persona persona;

    public Inquilino(Integer id, Integer identificador, String documento) {
        this.id = id;
        this.identificador = identificador;
        this.documento = documento;
    }

    public Inquilino() {
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public Unidad getIdentificadorInquilino() {
        return identificadorInquilino;
    }

    public void setIdentificadorInquilino(Unidad unidadByIdentificador_inquilino) {
        this.identificadorInquilino = unidadByIdentificador_inquilino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inquilino inquilino)) return false;
        return Objects.equals(getId(), inquilino.getId()) && Objects.equals(getDocumento(), inquilino.getDocumento()) && Objects.equals(getIdentificador(), inquilino.getIdentificador()) && Objects.equals(getIdentificadorInquilino(), inquilino.getIdentificadorInquilino());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDocumento(), getIdentificador(), getIdentificadorInquilino());
    }
}
