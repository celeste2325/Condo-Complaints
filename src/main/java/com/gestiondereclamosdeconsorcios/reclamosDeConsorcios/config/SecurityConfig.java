package com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.config;

import com.gestiondereclamosdeconsorcios.reclamosDeConsorcios.service.UserProvider;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessListener(final PasswordEncoder encoder) {

        return (AuthenticationSuccessEvent event) -> {
            final Authentication auth = event.getAuthentication();

            if (auth instanceof UsernamePasswordAuthenticationToken && auth.getCredentials() != null) {

                final CharSequence clearTextPass = (CharSequence) auth.getCredentials(); // 1
                final String newPasswordHash = encoder.encode(clearTextPass); // 2


                ((UsernamePasswordAuthenticationToken) auth).eraseCredentials(); // 3
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**/api/person/**/", "/**/api/auth/**/", "/**/api/duenio/**/", "/**/api/building/**/", "/**/api/unit/**/", "/**/api/complaint/**/", "/**/api/file/**/", "/**/api/image/**/")
                //.antMatchers("/**/api/user/**/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, new SecureRandom("cele".getBytes()));
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, UserProvider provide) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.eraseCredentials(false)
                .userDetailsService(provide)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.parentAuthenticationManager(null).build();
    }


    @Bean
    public UserDetailsService userDetailsService(UserProvider provider) {
        return provider;
    }
}
