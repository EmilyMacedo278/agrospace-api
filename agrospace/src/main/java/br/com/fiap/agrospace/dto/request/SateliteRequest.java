package br.com.fiap.agrospace.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SateliteRequest(

        @NotBlank(message = "O nome do satélite é obrigatório")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
        String nome,

        @NotBlank(message = "O tipo de monitoramento é obrigatório")
        @Size(max = 100, message = "O tipo de monitoramento deve ter no máximo 100 caracteres")
        String tipoMonitoramento,

        @NotBlank(message = "O status é obrigatório")
        @Size(max = 50, message = "O status deve ter no máximo 50 caracteres")
        String status
) {
}