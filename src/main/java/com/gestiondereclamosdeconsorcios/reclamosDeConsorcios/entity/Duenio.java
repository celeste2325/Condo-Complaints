package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "duenios", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Duenio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "identificador", updatable = false, insertable = false)
    private Integer identificador;

    @Basic
    @Column(name = "documento", updatable = false, insertable = false)
    private String documento;

    @ManyToOne
    @JoinColumn(name = "identificador", referencedColumnName = "identificador")
    @JsonBackReference(value = "unidad-duenio")
    private Unidad identificadorDuenio;

    @ManyToOne
    @JoinColumn(name = "documento", referencedColumnName = "documento")
    @JsonBackReference(value = "persona-duenio")
    private Persona persona;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona personaByDocumento) {
        this.persona = personaByDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

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
        return Objects.equals(getId(), duenio.getId()) && Objects.equals(getIdentificador(), duenio.getIdentificador()) && Objects.equals(getDocumento(), duenio.getDocumento()) && Objects.equals(getIdentificadorDuenio(), duenio.getIdentificadorDuenio()) && Objects.equals(getPersona(), duenio.getPersona());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdentificador(), getDocumento(), getIdentificadorDuenio(), getPersona());
    }
}
