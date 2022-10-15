package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "personas", schema = "dbo", catalog = "gestion_reclamo_consorcio")
public class Persona {

    @Id
    @Column(name = "documento")
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
    private String roles = "CLIENTE";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(getDocumento(), persona.getDocumento()) && Objects.equals(getNombre(), persona.getNombre()) && Objects.equals(getReclamosByDocumento(), persona.getReclamosByDocumento()) && Objects.equals(contrasenia, persona.contrasenia) && Objects.equals(roles, persona.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumento(), getNombre(), getReclamosByDocumento(), contrasenia, roles);
    }

    public Collection<Reclamo> getReclamosByDocumento() {
        return reclamosByDocumento;
    }

    public void setReclamosByDocumento(Collection<Reclamo> reclamosByDocumento) {
        this.reclamosByDocumento = reclamosByDocumento;
    }

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserGrantedAuthority admin = new UserGrantedAuthority("ADMIN");
        UserGrantedAuthority cliente = new UserGrantedAuthority("CLIENTE");
        Collection<UserGrantedAuthority> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(cliente);
        return roles;
    }*/

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }*/

    /*@Override
    public String getPassword() {
        return this.getContrasenia();
    }*/

    /*@Override
    public String getUsername() {
        return this.getDocumento();
    }*/

    /*@Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }*/
}
