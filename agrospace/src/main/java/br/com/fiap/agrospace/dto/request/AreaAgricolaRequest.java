package br.com.fiap.agrospace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AreaAgricolaRequest(

        @NotBlank(message = "O nome da área agrícola é obrigatório")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
        String nome,

        @NotBlank(message = "O tipo de cultivo é obrigatório")
        @Size(max = 80, message = "O tipo de cultivo deve ter no máximo 80 caracteres")
        String tipoCultivo,

        @NotNull(message = "O tamanho em hectares é obrigatório")
        @Positive(message = "O tamanho deve ser maior que zero")
        Double tamanhoHectares,

        @NotNull(message = "O ID da fazenda é obrigatório")
        Long fazendaId
) {
}