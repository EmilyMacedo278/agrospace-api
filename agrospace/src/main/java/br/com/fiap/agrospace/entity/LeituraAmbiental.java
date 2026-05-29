package br.com.fiap.agrospace.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_leitura_ambiental")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeituraAmbiental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double temperatura;

    @Column(nullable = false)
    private Double umidade;

    @Column(nullable = false)
    private Double indiceVegetacao;

    @Column(nullable = false, length = 50)
    private String nivelRisco;

    @Column(nullable = false)
    private LocalDateTime dataLeitura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_agricola_id", nullable = false)
    private AreaAgricola areaAgricola;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "satelite_id", nullable = false)
    private Satelite satelite;
}