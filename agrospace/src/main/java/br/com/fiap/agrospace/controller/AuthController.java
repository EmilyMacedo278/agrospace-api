package br.com.fiap.agrospace.controller;

import br.com.fiap.agrospace.dto.auth.LoginRequest;
import br.com.fiap.agrospace.dto.auth.LoginResponse;
import br.com.fiap.agrospace.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        if (!request.username().equals("admin") || !request.password().equals("admin123")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos");
        }

        String token = jwtUtil.gerarToken(request.username());

        return new LoginResponse(token, "Bearer");
    }
}