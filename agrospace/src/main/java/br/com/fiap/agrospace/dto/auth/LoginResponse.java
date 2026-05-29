package br.com.fiap.agrospace.dto.auth;

public record LoginResponse(
        String token,
        String type
) {
}