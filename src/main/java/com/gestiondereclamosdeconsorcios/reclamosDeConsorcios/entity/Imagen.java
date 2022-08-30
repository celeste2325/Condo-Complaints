package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

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
    @Basic
    @Column(name = "idReclamo")
    private Integer idReclamo;
    @ManyToOne
    @JoinColumn(name = "idReclamo", referencedColumnName = "idReclamo")
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

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagen that = (Imagen) o;
        return Objects.equals(numero, that.numero) && Objects.equals(path, that.path) && Objects.equals(tipo, that.tipo) && Objects.equals(idReclamo, that.idReclamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, path, tipo, idReclamo);
    }

    public Reclamo getReclamosByIdReclamo() {
        return reclamosByIdReclamo;
    }

    public void setReclamosByIdReclamo(Reclamo reclamosByIdReclamo) {
        this.reclamosByIdReclamo = reclamosByIdReclamo;
    }
}
