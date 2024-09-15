package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AuthDto {
    private String document;
    private String password;
}
