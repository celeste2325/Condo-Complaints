package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.config.UserGrantedAuthority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "people", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Person implements UserDetails {

    @Id
    @Column(name = "document")
    private String document;
    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "credential")
    private String credential;

    @Basic
    @Column(name = "role")
    private String role = "cliente";
    @OneToMany(mappedBy = "personByDocument")
    @JsonManagedReference(value = "complaint-person")
    private Collection<Complaint> complaintsByDocument;

    @OneToMany(mappedBy = "person")
    @JsonManagedReference(value = "person-duenio")
    private Collection<Duenio> dueniosByDocument;

    @OneToMany(mappedBy = "person")
    @JsonManagedReference(value = "person-inquilino")
    private Collection<Inquilino> inquilinosByDocument;

    public Person(String document, String credential) {
        this.document = document;
        this.credential = credential;
    }

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person persona)) return false;
        return Objects.equals(getDocument(), persona.getDocument()) && Objects.equals(getName(), persona.getName()) && Objects.equals(getCredential(), persona.getCredential()) && Objects.equals(getRole(), persona.getRole()) && Objects.equals(getComplaintsByDocument(), persona.getComplaintsByDocument()) && Objects.equals(getDueniosByDocument(), persona.getDueniosByDocument());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocument(), getName(), getCredential(), getRole(), getComplaintsByDocument(), getDueniosByDocument());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserGrantedAuthority rol = new UserGrantedAuthority(this.getRole());
        Collection<UserGrantedAuthority> roles = new ArrayList<>();
        roles.add(rol);
        return roles;
    }

    @Override
    public String getPassword() {
        return this.getCredential();
    }

    @Override
    public String getUsername() {
        return this.getDocument();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
