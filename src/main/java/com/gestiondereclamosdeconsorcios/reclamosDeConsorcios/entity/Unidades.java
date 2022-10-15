package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Unidades {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "identificador", nullable = false)
    private Integer identificador;
    @Basic
    @Column(name = "piso", nullable = false, length = 5)
    private String piso;
    @Basic
    @Column(name = "numero", nullable = false, length = 10)
    private String numero;
    @Basic
    @Column(name = "habitado", nullable = false, length = 1)
    private String habitado;
    @Basic
    @Column(name = "codigoEdificio", nullable = true,updatable = false,insertable = false)
    private Integer codigoEdificio;
    @ManyToOne
    @JoinColumn(name = "codigoEdificio", referencedColumnName = "codigo")
    @JsonManagedReference("edificio_unidad")
    private Edificios edificioByCodigoEdificios;

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

    public Integer getCodigoEdificio() {
        return codigoEdificio;
    }

    public void setCodigoEdificio(Integer codigoEdificio) {
        this.codigoEdificio = codigoEdificio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unidades unidades = (Unidades) o;

        if (identificador != null ? !identificador.equals(unidades.identificador) : unidades.identificador != null)
            return false;
        if (piso != null ? !piso.equals(unidades.piso) : unidades.piso != null) return false;
        if (numero != null ? !numero.equals(unidades.numero) : unidades.numero != null) return false;
        if (habitado != null ? !habitado.equals(unidades.habitado) : unidades.habitado != null) return false;
        if (codigoEdificio != null ? !codigoEdificio.equals(unidades.codigoEdificio) : unidades.codigoEdificio != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = identificador != null ? identificador.hashCode() : 0;
        result = 31 * result + (piso != null ? piso.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (habitado != null ? habitado.hashCode() : 0);
        result = 31 * result + (codigoEdificio != null ? codigoEdificio.hashCode() : 0);
        return result;
    }

    public Edificios getEdificiosByCodigoEdificio() {
        return edificioByCodigoEdificios;
    }

    public void setEdificiosByCodigoEdificio(Edificios edificioByCodigoEdificios) {
        this.edificioByCodigoEdificios = edificioByCodigoEdificios;
    }
}
