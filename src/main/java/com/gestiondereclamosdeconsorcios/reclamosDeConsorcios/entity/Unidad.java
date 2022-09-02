package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "unidades", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Unidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "identificador")
    private Integer identificador;
    @Basic
    @Column(name = "piso")
    private String piso;
    @Basic
    @Column(name = "numero")
    private String numero;
    @Basic
    @Column(name = "habitado")
    private String habitado;
    @ManyToOne
    @JoinColumn(name = "codigoEdificio", referencedColumnName = "codigo")
    @JsonBackReference(value = "edificio-unidad")
    private Edificio edificiosByCodigoEdificio;

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getHabitado() {
        return habitado;
    }

    public void setHabitado(String habitado) {
        this.habitado = habitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unidad that = (Unidad) o;
        return Objects.equals(identificador, that.identificador) && Objects.equals(piso, that.piso) && Objects.equals(numero, that.numero) && Objects.equals(habitado, that.habitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, piso, numero, habitado);
    }

    public Edificio getEdificiosByCodigoEdificio() {
        return edificiosByCodigoEdificio;
    }

    public void setEdificiosByCodigoEdificio(Edificio edificiosByCodigoEdificio) {
        this.edificiosByCodigoEdificio = edificiosByCodigoEdificio;
    }
}
