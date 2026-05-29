package br.com.fiap.agrospace.dto.response;

import java.time.LocalDateTime;

public record LeituraAmbientalResponse(
        Long id,
        Double temperatura,
        Double umidade,
        Double indiceVegetacao,
        String nivelRisco,
        LocalDateTime dataLeitura,
        Long areaAgricolaId,
        String nomeAreaAgricola,
        Long sateliteId,
        String nomeSatelite
) {
}