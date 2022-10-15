package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.config.security.UserGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Personas implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "documento", nullable = false, length = 20)
    private String documento;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "roles", nullable = false, length = 20)
    private String roles;
    @Basic
    @Column(name = "contrasenia", nullable = true, length = 60)
    private String contrasenia;
    @OneToMany(mappedBy = "personasByDocumento")
    @JsonManagedReference("personas_dueno")
    private Collection<Duenios> dueniosByDocumento;
    @OneToMany(mappedBy = "personasByDocumento")
    @JsonManagedReference("personas_inquilino")
    private Collection<Inquilinos> inquilinosByDocumento;
    @OneToMany(mappedBy = "personasByDocumento",fetch = FetchType.EAGER)
    @JsonManagedReference("personas_reclamo")
    private Collection<Reclamos> reclamosByDocumento;

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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personas personas = (Personas) o;

        if (documento != null ? !documento.equals(personas.documento) : personas.documento != null) return false;
        if (nombre != null ? !nombre.equals(personas.nombre) : personas.nombre != null) return false;
        if (roles != null ? !roles.equals(personas.roles) : personas.roles != null) return false;
        if (contrasenia != null ? !contrasenia.equals(personas.contrasenia) : personas.contrasenia != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = documento != null ? documento.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (contrasenia != null ? contrasenia.hashCode() : 0);
        return result;
    }

    public Collection<Duenios> getDueniosByDocumento() {
        return dueniosByDocumento;
    }

    public void setDueniosByDocumento(Collection<Duenios> dueniosByDocumento) {
        this.dueniosByDocumento = dueniosByDocumento;
    }

    public Collection<Inquilinos> getInquilinosByDocumento() {
        return inquilinosByDocumento;
    }

    public void setInquilinosByDocumento(Collection<Inquilinos> inquilinosByDocumento) {
        this.inquilinosByDocumento = inquilinosByDocumento;
    }

    public Collection<Reclamos> getReclamosByDocumento() {
        return reclamosByDocumento;
    }

    public void setReclamosByDocumento(Collection<Reclamos> reclamosByDocumento) {
        this.reclamosByDocumento = reclamosByDocumento;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserGrantedAuthority admin = new UserGrantedAuthority("ADMIN");
        UserGrantedAuthority cliente = new UserGrantedAuthority("CLIENTE");
        Collection<UserGrantedAuthority> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(cliente);
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
