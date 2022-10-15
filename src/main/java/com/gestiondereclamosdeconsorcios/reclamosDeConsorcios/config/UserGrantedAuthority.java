package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.config;

import org.springframework.security.core.GrantedAuthority;

public class UserGrantedAuthority implements GrantedAuthority {
    private String rol;
    public UserGrantedAuthority(String rol) {
        this.rol = rol;
    }

    @Override
    public String getAuthority() {
        return this.rol;
    }
}

