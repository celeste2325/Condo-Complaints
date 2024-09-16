package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "tenants", schema = "dbo", catalog = "condo_complaints")
public class Tenant {

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
    @JsonBackReference(value = "unit-tenant")
    private Unit unitIdTenant;

    @ManyToOne
    @JoinColumn(name = "document", referencedColumnName = "document")
    @JsonBackReference(value = "person-tenant")
    private Person person;

    public Tenant(Integer id, Integer unitID, String document) {
        this.id = id;
        this.unitID = unitID;
        this.document = document;
    }

    public Tenant() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tenant inquilino)) return false;
        return Objects.equals(getId(), inquilino.getId()) && Objects.equals(getDocument(), inquilino.getDocument()) && Objects.equals(getUnitID(), inquilino.getUnitID()) && Objects.equals(getUnitIdTenant(), inquilino.getUnitIdTenant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDocument(), getUnitID(), getUnitIdTenant());
    }
}
