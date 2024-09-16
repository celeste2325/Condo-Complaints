package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "owners", schema = "dbo", catalog = "condo_complaints")
public class Owner {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "unitID", updatable = false, insertable = false)
    private Integer unitID;

    @Basic
    @Column(name = "document", updatable = false, insertable = false)
    private String document;

    @ManyToOne
    @JoinColumn(name = "unitID", referencedColumnName = "unitID")
    @JsonBackReference(value = "unit-owner")
    private Unit unitIDowner;

    @ManyToOne
    @JoinColumn(name = "document", referencedColumnName = "document")
    @JsonBackReference(value = "person-owner")
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner owner)) return false;
        return Objects.equals(getId(), owner.getId()) && Objects.equals(getUnitID(), owner.getUnitID()) && Objects.equals(getDocument(), owner.getDocument()) && Objects.equals(getUnitIDowner(), owner.getUnitIDowner()) && Objects.equals(getPerson(), owner.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUnitID(), getDocument(), getUnitIDowner(), getPerson());
    }
}
