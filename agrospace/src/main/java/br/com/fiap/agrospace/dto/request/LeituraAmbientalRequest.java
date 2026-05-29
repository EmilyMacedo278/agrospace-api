package br.com.fiap.agrospace.dto.request;

import jakarta.validation.constraints.NotNull;

public record LeituraAmbientalRequest(

        @NotNull(message = "A temperatura é obrigatória")
        Double temperatura,

        @NotNull(message = "A umidade é obrigatória")
        Double umidade,

        @NotNull(message = "O índice de vegetação é obrigatório")
        Double indiceVegetacao,

        @NotNull(message = "O ID da área agrícola é obrigatório")
        Long areaAgricolaId,

        @NotNull(message = "O ID do satélite é obrigatório")
        Long sateliteId
) {
}