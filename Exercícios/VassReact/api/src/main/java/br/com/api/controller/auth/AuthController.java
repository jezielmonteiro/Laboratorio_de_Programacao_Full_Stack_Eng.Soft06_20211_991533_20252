package br.com.api.controller.auth;

import br.com.api.controller.auth.dto.LoginRequest;
import br.com.api.controller.auth.dto.TokenResponse;
import br.com.api.controller.auth.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/auth", produces="application/json")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthController(AuthenticationManager am, JwtService jwt){
        this.authManager=am;
        this.jwt=jwt;
    }

    @PostMapping(value="/login", consumes="application/json")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest body) {
        Authentication auth = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
        Authentication result = authManager.authenticate(auth); // lança se inválido (401)
        String token = jwt.generateToken(result.getName(), result.getAuthorities());

        return ResponseEntity.ok(new TokenResponse(token));
    }
}