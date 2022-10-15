package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;

@Entity
public class Inquilinos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "identificador", nullable = false)
    private Integer identificador;
    @Basic
    @Column(name = "documento", nullable = false, length = 20, insertable = false,updatable = false)
    private String documento;
    @ManyToOne
    @JoinColumn(name = "documento", referencedColumnName = "documento", nullable = false)
    private Personas personasByDocumento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inquilinos that = (Inquilinos) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (identificador != null ? !identificador.equals(that.identificador) : that.identificador != null)
            return false;
        if (documento != null ? !documento.equals(that.documento) : that.documento != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (identificador != null ? identificador.hashCode() : 0);
        result = 31 * result + (documento != null ? documento.hashCode() : 0);
        return result;
    }

    public Personas getPersonasByDocumento() {
        return personasByDocumento;
    }

    public void setPersonasByDocumento(Personas personasByDocumento) {
        this.personasByDocumento = personasByDocumento;
    }
}
