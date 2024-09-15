package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "inquilinos", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Inquilino {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "document", updatable = false, insertable = false)
    private String document;

    @Basic
    @Column(name = "unitID", updatable = false, insertable = false)
    private Integer unitID;

    @ManyToOne
    @JoinColumn(name = "unitID", referencedColumnName = "unitID")
    @JsonBackReference(value = "unit-inquilino")
    private Unit unitIDInquilino;

    @ManyToOne
    @JoinColumn(name = "document", referencedColumnName = "document")
    @JsonBackReference(value = "person-inquilino")
    private Person person;

    public Inquilino(Integer id, Integer unitID, String document) {
        this.id = id;
        this.unitID = unitID;
        this.document = document;
    }

    public Inquilino() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inquilino inquilino)) return false;
        return Objects.equals(getId(), inquilino.getId()) && Objects.equals(getDocument(), inquilino.getDocument()) && Objects.equals(getUnitID(), inquilino.getUnitID()) && Objects.equals(getUnitIDInquilino(), inquilino.getUnitIDInquilino());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDocument(), getUnitID(), getUnitIDInquilino());
    }
}
