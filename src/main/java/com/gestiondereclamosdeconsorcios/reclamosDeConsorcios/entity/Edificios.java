package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Edificios {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;
    @OneToMany(mappedBy = "edificiosByCodigo")
    @JsonManagedReference("reclamo_edifcio")
    private Collection<Reclamos> reclamosByCodigo;
    @OneToMany(mappedBy = "edificioByCodigoEdificios")
    @JsonBackReference("edificio_unidad")
    private Collection<Unidades> unidadesByCodigo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edificios edificios = (Edificios) o;

        if (codigo != null ? !codigo.equals(edificios.codigo) : edificios.codigo != null) return false;
        if (nombre != null ? !nombre.equals(edificios.nombre) : edificios.nombre != null) return false;
        if (direccion != null ? !direccion.equals(edificios.direccion) : edificios.direccion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        return result;
    }

    public Collection<Reclamos> getReclamosByCodigo() {
        return reclamosByCodigo;
    }

    public void setReclamosByCodigo(Collection<Reclamos> reclamosByCodigo) {
        this.reclamosByCodigo = reclamosByCodigo;
    }

    public Collection<Unidades> getUnidadesByCodigo() {
        return unidadesByCodigo;
    }

    public void setUnidadesByCodigo(Collection<Unidades> unidadesByCodigo) {
        this.unidadesByCodigo = unidadesByCodigo;
    }
}
