package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "personas", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Persona {

    @Id
    @Column(name = "documento")
    private String documento;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "personasByDocumento")
    private Collection<Reclamo> reclamosByDocumento;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(getDocumento(), persona.getDocumento()) && Objects.equals(getNombre(), persona.getNombre()) && Objects.equals(getReclamosByDocumento(), persona.getReclamosByDocumento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumento(), getNombre(), getReclamosByDocumento());
    }

    public Collection<Reclamo> getReclamosByDocumento() {
        return reclamosByDocumento;
    }

    public void setReclamosByDocumento(Collection<Reclamo> reclamosByDocumento) {
        this.reclamosByDocumento = reclamosByDocumento;
    }
}
