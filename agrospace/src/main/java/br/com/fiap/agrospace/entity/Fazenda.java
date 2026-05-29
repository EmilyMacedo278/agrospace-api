package br.com.fiap.agrospace.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_fazenda")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fazenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 160)
    private String localizacao;

    @Column(nullable = false, length = 120)
    private String responsavel;

    @OneToMany(mappedBy = "fazenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AreaAgricola> areasAgricolas = new ArrayList<>();
}