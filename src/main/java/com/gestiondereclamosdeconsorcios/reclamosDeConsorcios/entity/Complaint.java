package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "complaints", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Complaint {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "complaintID")
    private Integer complaintID;

    @Basic
    @Column(name = "status")
    private String status = "nuevo";
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "buildingID")
    private Integer buildingID;
    @Basic
    @Column(name = "unitID")
    private Integer unitID;
    @OneToMany(mappedBy = "complaintsByComplaintID", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "complaint-image")
    private Collection<Image> imagesByComplaintID = new ArrayList<>();
    @ManyToOne()
    @JoinColumn(name = "document", referencedColumnName = "document", nullable = false)
    @JsonBackReference(value = "complaint-person")
    private Person personByDocument;
    @ManyToOne
    @JoinColumn(name = "buildingID", referencedColumnName = "buildingID", insertable = false, updatable = false)
    @JsonBackReference(value = "building-complaint")
    private Building buildingsByBuildingID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint that = (Complaint) o;
        return Objects.equals(complaintID, that.complaintID) && Objects.equals(location, that.location) && Objects.equals(description, that.description) && Objects.equals(unitID, that.unitID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(complaintID, location, description, unitID);
    }

}
