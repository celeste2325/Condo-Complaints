package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inquilinos", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Inquilino {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", columnDefinition = "INTEGER")
    private Integer id;
    @Basic
    @Column(name = "identificador")
    private Integer identificador;
    @OneToOne
    @JoinColumn(name = "documento", referencedColumnName = "documento")
    private Persona persona;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inquilino inquilino)) return false;
        return Objects.equals(getId(), inquilino.getId()) && Objects.equals(getIdentificador(), inquilino.getIdentificador()) && Objects.equals(getPersona(), inquilino.getPersona());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdentificador(), getPersona());
    }
}
