package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "units", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Unit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "unitID")
    private Integer unitID;
    @Basic
    @Column(name = "floor")
    private String floor;
    @Basic
    @Column(name = "number")
    private String number;
    @Basic
    @Column(name = "habitado")
    private String habitado = "N";
    @Basic
    @Column(name = "buildingID", insertable = false, updatable = false)
    private Integer buildingID;
    @ManyToOne
    @JoinColumn(name = "buildingID", referencedColumnName = "buildingID")
    @JsonBackReference(value = "building-unit")
    private Building buildingByBuildingID;

    @OneToMany(mappedBy = "unitIDInquilino")
    @JsonManagedReference(value = "unit-inquilino")
    private Collection<Inquilino> inquilinosByUnitID;

    @OneToMany(mappedBy = "unitIDduenio")
    @JsonManagedReference(value = "unit-duenio")
    private Collection<Duenio> duniosByUnitID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit unit)) return false;
        return Objects.equals(getUnitID(), unit.getUnitID()) && Objects.equals(getFloor(), unit.getFloor()) && Objects.equals(getNumber(), unit.getNumber()) && Objects.equals(getHabitado(), unit.getHabitado()) && Objects.equals(getBuildingByBuildingID(), unit.getBuildingByBuildingID()) && Objects.equals(getInquilinosByUnitID(), unit.getInquilinosByUnitID()) && Objects.equals(getDuniosByUnitID(), unit.getDuniosByUnitID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnitID(), getFloor(), getNumber(), getHabitado(), getBuildingByBuildingID(), getInquilinosByUnitID(), getDuniosByUnitID());
    }
}
