package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.config.security;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.entity.Persona;
import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

@Configuration
//@EnableWebSecurity
public class EncryptionConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, new SecureRandom("cele".getBytes()));
    }

    //@Bean
    public AuthenticationManager authManager(HttpSecurity http,UserProvider provide) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.eraseCredentials(false)
                .userDetailsService(provide)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }



   // @Bean
    public UserDetailsService userDetailsService(UserProvider provider) {
        return provider;
    }

}
