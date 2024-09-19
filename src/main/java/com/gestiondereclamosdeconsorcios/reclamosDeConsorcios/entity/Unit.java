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
@Table(name = "units", schema = "dbo", catalog = "condo_complaints")
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
    @Column(name = "occupied")
    private String occupied = "N";
    @Basic
    @Column(name = "buildingID", insertable = false, updatable = false)
    private Integer buildingID;
    @ManyToOne
    @JoinColumn(name = "buildingID", referencedColumnName = "buildingID")
    @JsonBackReference(value = "building-unit")
    private Building buildingByBuildingID;

    @OneToMany(mappedBy = "unitIdTenant")
    @JsonManagedReference(value = "unit-tenant")
    private Collection<Tenant> tenantsByUnitID;

    @OneToMany(mappedBy = "unitIDowner")
    @JsonManagedReference(value = "unit-owner")
    private Collection<Owner> ownersByUnitID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit unit)) return false;
        return Objects.equals(getUnitID(), unit.getUnitID()) && Objects.equals(getFloor(), unit.getFloor()) && Objects.equals(getNumber(), unit.getNumber()) && Objects.equals(getOccupied(), unit.getOccupied()) && Objects.equals(getBuildingByBuildingID(), unit.getBuildingByBuildingID()) && Objects.equals(getTenantsByUnitID(), unit.getTenantsByUnitID()) && Objects.equals(getOwnersByUnitID(), unit.getOwnersByUnitID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnitID(), getFloor(), getNumber(), getOccupied(), getBuildingByBuildingID(), getTenantsByUnitID(), getOwnersByUnitID());
    }
}
