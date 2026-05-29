package br.com.fiap.agrospace.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_satelite")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Satelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 100)
    private String tipoMonitoramento;

    @Column(nullable = false, length = 50)
    private String status;

    @OneToMany(mappedBy = "satelite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeituraAmbiental> leiturasAmbientais = new ArrayList<>();
}