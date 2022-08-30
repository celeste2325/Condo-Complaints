package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inquilinos", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Inquilino {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "identificador")
    private Integer identificador;
    @Basic
    @Column(name = "documento")
    private String documento;

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
        Inquilino that = (Inquilino) o;
        return Objects.equals(id, that.id) && Objects.equals(identificador, that.identificador) && Objects.equals(documento, that.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificador, documento);
    }
}
