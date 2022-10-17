package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "imagenes", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Imagen {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numero")
    private Integer numero;

    @Lob
    @Column(name = "dataFoto")
    private byte[] dataFoto;
    @Basic
    @Column(name = "tipo")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "idReclamo", referencedColumnName = "idReclamo")
    @JsonBackReference(value = "reclamo-imagen")
    private Reclamo reclamosByIdReclamo = new Reclamo();
    @JsonInclude
    @Transient
    private String castBlob;

    public String getCastBlob() {
        return castBlob;
    }

    public void setCastBlob(String castBlob) {
        this.castBlob = castBlob;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public byte[] getDataFoto() {
        return dataFoto;
    }

    public void setDataFoto(byte[] path) {
        this.dataFoto = path;
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
        return Objects.equals(numero, that.numero) && Objects.equals(dataFoto, that.dataFoto) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, dataFoto, tipo);
    }

    public Reclamo getReclamosByIdReclamo() {
        return reclamosByIdReclamo;
    }

    public void setReclamosByIdReclamo(Reclamo reclamosByIdReclamo) {
        this.reclamosByIdReclamo = reclamosByIdReclamo;
    }
}
