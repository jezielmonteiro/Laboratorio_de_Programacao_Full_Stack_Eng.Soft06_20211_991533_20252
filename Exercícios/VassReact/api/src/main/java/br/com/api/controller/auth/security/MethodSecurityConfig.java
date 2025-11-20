package br.com.api.controller.auth.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity // habilita @PreAuthorize, @PostAuthorize etc.
public class MethodSecurityConfig {
    // vazio mesmo; só serve para ligar as anotações
}