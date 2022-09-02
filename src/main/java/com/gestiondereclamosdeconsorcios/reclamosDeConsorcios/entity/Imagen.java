package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "imagenes", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Imagen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numero")
    private Integer numero;
    @Basic
    @Column(name = "path")
    private String path;
    @Basic
    @Column(name = "tipo")
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "idReclamo", referencedColumnName = "idReclamo")
    @JsonBackReference(value = "reclamo-imagen")
    private Reclamo reclamosByIdReclamo;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagen that = (Imagen) o;
        return Objects.equals(numero, that.numero) && Objects.equals(path, that.path) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, path, tipo);
    }

    public Reclamo getReclamosByIdReclamo() {
        return reclamosByIdReclamo;
    }

    public void setReclamosByIdReclamo(Reclamo reclamosByIdReclamo) {
        this.reclamosByIdReclamo = reclamosByIdReclamo;
    }
}
