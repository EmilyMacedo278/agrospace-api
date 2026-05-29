package br.com.fiap.agrospace.dto.response;

public record FazendaResponse(
        Long id,
        String nome,
        String cidade,
        String estado,
        String pais,
        String responsavel
) {
}