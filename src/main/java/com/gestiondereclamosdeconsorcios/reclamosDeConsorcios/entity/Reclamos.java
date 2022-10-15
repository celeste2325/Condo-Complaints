package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Reclamos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idReclamo", nullable = false)
    private Integer idReclamo;
    @Basic
    @Column(name = "documento", nullable = false, length = 20,insertable = false,updatable = false)
    private String documento;
    @Basic
    @Column(name = "codigo", nullable = false,updatable = false,insertable = false)
    private Integer codigo;
    @Basic
    @Column(name = "ubicacion", nullable = true, length = 300)
    private String ubicacion;
    @Basic
    @Column(name = "descripcion", nullable = true, length = 1000)
    private String descripcion;
    @Basic
    @Column(name = "identificador", nullable = true)
    private Integer identificador;
    @Basic
    @Column(name = "estado", nullable = true, length = 50)
    private String estado;
    @OneToMany(mappedBy = "reclamosByIdReclamo")
    private Collection<Imagenes> imagenesByIdReclamo;
    @ManyToOne
    @JoinColumn(name = "documento", referencedColumnName = "documento", nullable = false)
    @JsonBackReference("personas_reclamo")
    private Personas personasByDocumento;
    @ManyToOne
    @JoinColumn(name = "codigo", referencedColumnName = "codigo", nullable = false)
    @JsonBackReference("reclamo_edifcio")
    private Edificios edificiosByCodigo;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reclamos reclamos = (Reclamos) o;

        if (idReclamo != null ? !idReclamo.equals(reclamos.idReclamo) : reclamos.idReclamo != null) return false;
        if (documento != null ? !documento.equals(reclamos.documento) : reclamos.documento != null) return false;
        if (codigo != null ? !codigo.equals(reclamos.codigo) : reclamos.codigo != null) return false;
        if (ubicacion != null ? !ubicacion.equals(reclamos.ubicacion) : reclamos.ubicacion != null) return false;
        if (descripcion != null ? !descripcion.equals(reclamos.descripcion) : reclamos.descripcion != null)
            return false;
        if (identificador != null ? !identificador.equals(reclamos.identificador) : reclamos.identificador != null)
            return false;
        if (estado != null ? !estado.equals(reclamos.estado) : reclamos.estado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReclamo != null ? idReclamo.hashCode() : 0;
        result = 31 * result + (documento != null ? documento.hashCode() : 0);
        result = 31 * result + (codigo != null ? codigo.hashCode() : 0);
        result = 31 * result + (ubicacion != null ? ubicacion.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (identificador != null ? identificador.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    public Collection<Imagenes> getImagenesByIdReclamo() {
        return imagenesByIdReclamo;
    }

    public void setImagenesByIdReclamo(Collection<Imagenes> imagenesByIdReclamo) {
        this.imagenesByIdReclamo = imagenesByIdReclamo;
    }

    public Personas getPersonasByDocumento() {
        return personasByDocumento;
    }

    public void setPersonasByDocumento(Personas personasByDocumento) {
        this.personasByDocumento = personasByDocumento;
    }

    public Edificios getEdificiosByCodigo() {
        return edificiosByCodigo;
    }

    public void setEdificiosByCodigo(Edificios edificiosByCodigo) {
        this.edificiosByCodigo = edificiosByCodigo;
    }
}
