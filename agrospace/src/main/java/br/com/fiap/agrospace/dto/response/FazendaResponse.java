package br.com.fiap.agrospace.dto.response;

public record FazendaResponse(
        Long id,
        String nome,
        String localizacao,
        String responsavel
) {
}