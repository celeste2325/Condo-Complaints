package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.config.UserGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "personas", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Persona implements UserDetails{

    @Id
    @Column(name = "documento")
    private String documento;
    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "contrasenia")
    private String contrasenia;

    @Basic
    @Column(name = "roles")
    private String roles = "cliente";
    @OneToMany(mappedBy = "personasByDocumento")
    @JsonManagedReference(value = "reclamo-persona")
    private Collection<Reclamo> reclamosByDocumento;

    @OneToMany(mappedBy = "persona")
    @JsonManagedReference(value = "persona-duenio")
    private Collection<Duenio> dueniosByDocumento;

    @OneToMany(mappedBy = "persona")
    @JsonManagedReference(value = "persona-inquilino")
    private Collection<Inquilino> inquilinosByDocumento;

    public Persona(String documento, String contrasenia) {
        this.documento = documento;
        this.contrasenia = contrasenia;
    }

    public Persona() {
    }

    public Collection<Inquilino> getInquilinosByDocumento() {
        return inquilinosByDocumento;
    }

    public void setInquilinosByDocumento(Collection<Inquilino> inquilinosByDocumento) {
        this.inquilinosByDocumento = inquilinosByDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Collection<Duenio> getDueniosByDocumento() {
        return dueniosByDocumento;
    }

    public void setDueniosByDocumento(Collection<Duenio> dueniosByDocumento) {
        this.dueniosByDocumento = dueniosByDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(getDocumento(), persona.getDocumento()) && Objects.equals(getNombre(), persona.getNombre()) && Objects.equals(getContrasenia(), persona.getContrasenia()) && Objects.equals(getRoles(), persona.getRoles()) && Objects.equals(getReclamosByDocumento(), persona.getReclamosByDocumento()) && Objects.equals(getDueniosByDocumento(), persona.getDueniosByDocumento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumento(), getNombre(), getContrasenia(), getRoles(), getReclamosByDocumento(), getDueniosByDocumento());
    }

    public Collection<Reclamo> getReclamosByDocumento() {
        return reclamosByDocumento;
    }

    public void setReclamosByDocumento(Collection<Reclamo> reclamosByDocumento) {
        this.reclamosByDocumento = reclamosByDocumento;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserGrantedAuthority rol = new UserGrantedAuthority(this.getRoles());
        Collection<UserGrantedAuthority> roles = new ArrayList<>();
        roles.add(rol);
        return roles;
    }

    @Override
    public String getPassword() {
        return this.getContrasenia();
    }

    @Override
    public String getUsername() {
        return this.getDocumento();
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
