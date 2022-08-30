package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "personas", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Persona {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        if (o == null || getClass() != o.getClass()) return false;
        Persona that = (Persona) o;
        return Objects.equals(documento, that.documento) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento, nombre);
    }

    public Collection<Reclamo> getReclamosByDocumento() {
        return reclamosByDocumento;
    }

    public void setReclamosByDocumento(Collection<Reclamo> reclamosByDocumento) {
        this.reclamosByDocumento = reclamosByDocumento;
    }
}
