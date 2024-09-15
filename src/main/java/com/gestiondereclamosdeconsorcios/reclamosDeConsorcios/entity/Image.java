package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "images", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "path")
    private String path;
    @Basic
    @Column(name = "extension")
    private String extension;

    @ManyToOne
    @JoinColumn(name = "complaintID", referencedColumnName = "complaintID")
    @JsonBackReference(value = "complaint-image")
    private Complaint complaintsByComplaintID = new Complaint();
    @JsonInclude
    @Transient
    private String castBlob;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image that = (Image) o;
        return Objects.equals(id, that.id) && Objects.equals(path, that.path) && Objects.equals(extension, that.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, extension);
    }

}
