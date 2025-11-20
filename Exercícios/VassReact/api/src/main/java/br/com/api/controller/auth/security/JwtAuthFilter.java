package br.com.api.controller.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        // 1) Ler o header Authorization
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response); // segue anônimo
            return;
        }

        // 2) Extrair token e validar
        String token = header.substring(7);
        try {
            Jws<Claims> jws = jwtService.parse(token);
            String username = jws.getBody().getSubject();
            String rolesCsv = (String) jws.getBody().get("roles");

            List<SimpleGrantedAuthority> authorities =
                    (rolesCsv == null || rolesCsv.isBlank())
                            ? List.of()
                            : Arrays.stream(rolesCsv.split(","))
                            .map(SimpleGrantedAuthority::new)
                            .toList();

            // 3) Construir Authentication e colocar no SecurityContext
            var authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));

            org.springframework.security.core.context.SecurityContextHolder
                    .getContext().setAuthentication(authentication);

        } catch (Exception ex) {
            // Token inválido/expirado -> não autentica (ficará 401 no entry point)
            // Opcional: logar ex.getMessage()
        }

        chain.doFilter(request, response);
    }
}