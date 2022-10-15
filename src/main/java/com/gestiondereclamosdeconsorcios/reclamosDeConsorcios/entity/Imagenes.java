package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Imagenes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numero", nullable = false)
    private Integer numero;
    @Basic
    @Column(name = "tipo", nullable = true, length = 10)
    private String tipo;
    @Basic
    @Column(name = "idReclamo", nullable = true,insertable = false,updatable = false)
    private Integer idReclamo;
    @Basic
    @Column(name = "dataFoto", nullable = false)
    private byte[] dataFoto;
    @ManyToOne
    @JoinColumn(name = "idReclamo", referencedColumnName = "idReclamo")
    private Reclamos reclamosByIdReclamo;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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

    public byte[] getDataFoto() {
        return dataFoto;
    }

    public void setDataFoto(byte[] dataFoto) {
        this.dataFoto = dataFoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Imagenes imagenes = (Imagenes) o;

        if (numero != null ? !numero.equals(imagenes.numero) : imagenes.numero != null) return false;
        if (tipo != null ? !tipo.equals(imagenes.tipo) : imagenes.tipo != null) return false;
        if (idReclamo != null ? !idReclamo.equals(imagenes.idReclamo) : imagenes.idReclamo != null) return false;
        if (!Arrays.equals(dataFoto, imagenes.dataFoto)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numero != null ? numero.hashCode() : 0;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (idReclamo != null ? idReclamo.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(dataFoto);
        return result;
    }

    public Reclamos getReclamosByIdReclamo() {
        return reclamosByIdReclamo;
    }

    public void setReclamosByIdReclamo(Reclamos reclamosByIdReclamo) {
        this.reclamosByIdReclamo = reclamosByIdReclamo;
    }
}
