package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "duenios", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Duenio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "identificador")
    private Integer identificador;

    @OneToOne()
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
        if (!(o instanceof Duenio duenio)) return false;
        return Objects.equals(getId(), duenio.getId()) && Objects.equals(getIdentificador(), duenio.getIdentificador()) && Objects.equals(persona, duenio.persona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdentificador(), persona);
    }
}
