package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "duenios", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Duenio {
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
    @JsonBackReference(value = "unit-duenio")
    private Unit unitIDduenio;

    @ManyToOne
    @JoinColumn(name = "document", referencedColumnName = "document")
    @JsonBackReference(value = "person-duenio")
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Duenio duenio)) return false;
        return Objects.equals(getId(), duenio.getId()) && Objects.equals(getUnitID(), duenio.getUnitID()) && Objects.equals(getDocument(), duenio.getDocument()) && Objects.equals(getUnitIDduenio(), duenio.getUnitIDduenio()) && Objects.equals(getPerson(), duenio.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUnitID(), getDocument(), getUnitIDduenio(), getPerson());
    }
}
