package br.com.fiap.agrospace.dto.response;

public record AreaAgricolaResponse(
        Long id,
        String nome,
        String tipoCultivo,
        Double tamanhoHectares,
        Long fazendaId,
        String nomeFazenda
) {
}