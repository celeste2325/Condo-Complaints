package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "buildings", schema = "dbo", catalog = "condo_complaints")
public class Building {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "buildingID")
    private Integer buildingID;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "buildingsByBuildingID")
    @JsonManagedReference(value = "building-complaint")
    private Collection<Complaint> complaintsByBuildingID;
    @OneToMany(mappedBy = "buildingByBuildingID")
    @JsonManagedReference(value = "building-unit")
    private Collection<Unit> unitsByBuildingID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building that = (Building) o;
        return Objects.equals(buildingID, that.buildingID) && Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingID, name, address);
    }

}
