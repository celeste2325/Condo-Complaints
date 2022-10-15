package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.config.security.UserGrantedAuthority;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "personas", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Persona implements UserDetails {

    @Id
    @Column(name = "documento")
    @EqualsAndHashCode.Include
    private String documento;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "personasByDocumento")
    private Collection<Reclamo> reclamosByDocumento;

    @Basic
    @Column(name = "contrasenia")
    private String contrasenia;

    @Basic
    @Column(name = "roles")
    private String roles;

    public Persona(String documento, String contrasenia) {
        this.documento = documento;
        this.contrasenia = contrasenia;
    }

    public Persona() {
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



    public Collection<Reclamo> getReclamosByDocumento() {
        return reclamosByDocumento;
    }

    public void setReclamosByDocumento(Collection<Reclamo> reclamosByDocumento) {
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
        return false;
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
