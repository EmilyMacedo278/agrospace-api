package br.com.fiap.agrospace.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_area_agricola")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaAgricola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 80)
    private String tipoCultivo;

    @Column(nullable = false)
    private Double tamanhoHectares;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fazenda_id", nullable = false)
    private Fazenda fazenda;

    @OneToMany(mappedBy = "areaAgricola", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeituraAmbiental> leiturasAmbientais = new ArrayList<>();
}