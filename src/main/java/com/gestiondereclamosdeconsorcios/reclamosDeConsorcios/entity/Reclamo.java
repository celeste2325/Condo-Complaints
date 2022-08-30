package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "reclamos", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Reclamo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idReclamo")
    private Integer idReclamo;
    @Basic
    @Column(name = "documento")
    private String documento;
    @Basic
    @Column(name = "codigo")
    private Integer codigo;
    @Basic
    @Column(name = "ubicacion")
    private String ubicacion;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @Basic
    @Column(name = "identificador")
    private Integer identificador;
    @OneToMany(mappedBy = "reclamosByIdReclamo")
    private Collection<Imagen> imagenesByIdReclamo;
    @ManyToOne
    @JoinColumn(name = "documento", referencedColumnName = "documento", nullable = false)
    private Persona personasByDocumento;
    @ManyToOne
    @JoinColumn(name = "codigo", referencedColumnName = "codigo", nullable = false)
    private Edificio edificiosByCodigo;

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reclamo that = (Reclamo) o;
        return Objects.equals(idReclamo, that.idReclamo) && Objects.equals(documento, that.documento) && Objects.equals(codigo, that.codigo) && Objects.equals(ubicacion, that.ubicacion) && Objects.equals(descripcion, that.descripcion) && Objects.equals(identificador, that.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReclamo, documento, codigo, ubicacion, descripcion, identificador);
    }

    public Collection<Imagen> getImagenesByIdReclamo() {
        return imagenesByIdReclamo;
    }

    public void setImagenesByIdReclamo(Collection<Imagen> imagenesByIdReclamo) {
        this.imagenesByIdReclamo = imagenesByIdReclamo;
    }

    public Persona getPersonasByDocumento() {
        return personasByDocumento;
    }

    public void setPersonasByDocumento(Persona personasByDocumento) {
        this.personasByDocumento = personasByDocumento;
    }

    public Edificio getEdificiosByCodigo() {
        return edificiosByCodigo;
    }

    public void setEdificiosByCodigo(Edificio edificiosByCodigo) {
        this.edificiosByCodigo = edificiosByCodigo;
    }
}
