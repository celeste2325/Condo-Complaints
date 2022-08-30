package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "edificios", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Edificio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo")
    private Integer codigo;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(mappedBy = "edificiosByCodigo")
    private Collection<Reclamo> reclamosByCodigo;
    @OneToMany(mappedBy = "edificiosByCodigoEdificio")
    private Collection<Unidad> unidadesByCodigo;

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
        Edificio that = (Edificio) o;
        return Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre) && Objects.equals(direccion, that.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, direccion);
    }

    public Collection<Reclamo> getReclamosByCodigo() {
        return reclamosByCodigo;
    }

    public void setReclamosByCodigo(Collection<Reclamo> reclamosByCodigo) {
        this.reclamosByCodigo = reclamosByCodigo;
    }

    public Collection<Unidad> getUnidadesByCodigo() {
        return unidadesByCodigo;
    }

    public void setUnidadesByCodigo(Collection<Unidad> unidadesByCodigo) {
        this.unidadesByCodigo = unidadesByCodigo;
    }
}
