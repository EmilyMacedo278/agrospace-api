package br.com.fiap.agrospace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FazendaRequest(

        @NotBlank(message = "O nome da fazenda é obrigatório")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
        String nome,

        @NotBlank(message = "A localização é obrigatória")
        @Size(max = 160, message = "A localização deve ter no máximo 160 caracteres")
        String localizacao,

        @NotBlank(message = "O responsável é obrigatório")
        @Size(max = 120, message = "O responsável deve ter no máximo 120 caracteres")
        String responsavel
) {
}