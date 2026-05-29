package br.com.fiap.agrospace.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Localizacao {

    private String cidade;
    private String estado;
    private String pais;
}