package br.com.fiap.agrospace.dto.response;

public record SateliteResponse(
        Long id,
        String nome,
        String tipoMonitoramento,
        String status
) {
}