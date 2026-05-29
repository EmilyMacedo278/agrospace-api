package br.com.fiap.agrospace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FazendaRequest(

        @NotBlank(message = "O nome da fazenda é obrigatório")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
        String nome,

        @NotBlank(message = "A cidade é obrigatória")
        @Size(max = 80, message = "A cidade deve ter no máximo 80 caracteres")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        @Size(max = 80, message = "O estado deve ter no máximo 80 caracteres")
        String estado,

        @NotBlank(message = "O país é obrigatório")
        @Size(max = 80, message = "O país deve ter no máximo 80 caracteres")
        String pais,

        @NotBlank(message = "O responsável é obrigatório")
        @Size(max = 120, message = "O responsável deve ter no máximo 120 caracteres")
        String responsavel
) {
}