package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
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
    private String habitado = "N";
    @Basic
    @Column(name = "codigoEdificio", insertable = false, updatable = false)
    private Integer codigoEdificio;
    @ManyToOne
    @JoinColumn(name = "codigoEdificio", referencedColumnName = "codigo")
    @JsonBackReference(value = "edificio-unidad")
    private Edificio edificiosByCodigoEdificio;

    @OneToMany(mappedBy = "identificadorInquilino")
    @JsonManagedReference(value = "unidad-inquilino")
    private Collection<Inquilino> inquilinosByIdentificador;

    @OneToMany(mappedBy = "identificadorDuenio")
    @JsonManagedReference(value = "unidad-duenio")
    private Collection<Duenio> duniosByIdentificador;

    public Collection<Inquilino> getInquilinosByIdentificador() {
        return inquilinosByIdentificador;
    }

    public void setInquilinosByIdentificador(Collection<Inquilino> inquilinoByIdentificador) {
        this.inquilinosByIdentificador = inquilinoByIdentificador;
    }

    public Integer getCodigoEdificio() {
        return codigoEdificio;
    }

    public void setCodigoEdificio(Integer codigoEdificio) {
        this.codigoEdificio = codigoEdificio;
    }

    public Collection<Duenio> getDuniosByIdentificador() {
        return duniosByIdentificador;
    }

    public void setDuniosByIdentificador(Collection<Duenio> duniosByIdentificador) {
        this.duniosByIdentificador = duniosByIdentificador;
    }

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

    public Edificio getEdificiosByCodigoEdificio() {
        return edificiosByCodigoEdificio;
    }

    public void setEdificiosByCodigoEdificio(Edificio edificiosByCodigoEdificio) {
        this.edificiosByCodigoEdificio = edificiosByCodigoEdificio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unidad unidad)) return false;
        return Objects.equals(getIdentificador(), unidad.getIdentificador()) && Objects.equals(getPiso(), unidad.getPiso()) && Objects.equals(getNumero(), unidad.getNumero()) && Objects.equals(getHabitado(), unidad.getHabitado()) && Objects.equals(getEdificiosByCodigoEdificio(), unidad.getEdificiosByCodigoEdificio()) && Objects.equals(getInquilinosByIdentificador(), unidad.getInquilinosByIdentificador()) && Objects.equals(getDuniosByIdentificador(), unidad.getDuniosByIdentificador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentificador(), getPiso(), getNumero(), getHabitado(), getEdificiosByCodigoEdificio(), getInquilinosByIdentificador(), getDuniosByIdentificador());
    }
}
