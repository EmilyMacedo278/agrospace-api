package br.com.fiap.agrospace.repository;

import br.com.fiap.agrospace.entity.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FazendaRepository extends JpaRepository<Fazenda, Long> {
}