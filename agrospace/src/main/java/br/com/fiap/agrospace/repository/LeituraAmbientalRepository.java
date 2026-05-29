package br.com.fiap.agrospace.repository;

import br.com.fiap.agrospace.entity.LeituraAmbiental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeituraAmbientalRepository extends JpaRepository<LeituraAmbiental, Long> {
}