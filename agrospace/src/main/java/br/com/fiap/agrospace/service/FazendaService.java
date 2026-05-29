package br.com.fiap.agrospace.service;

import br.com.fiap.agrospace.dto.request.FazendaRequest;
import br.com.fiap.agrospace.dto.response.FazendaResponse;
import br.com.fiap.agrospace.entity.Fazenda;
import br.com.fiap.agrospace.exception.ResourceNotFoundException;
import br.com.fiap.agrospace.repository.FazendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FazendaService {

    private final FazendaRepository fazendaRepository;

    public FazendaResponse criar(FazendaRequest request) {
        Fazenda fazenda = Fazenda.builder()
                .nome(request.nome())
                .localizacao(request.localizacao())
                .responsavel(request.responsavel())
                .build();

        Fazenda salva = fazendaRepository.save(fazenda);
        return toResponse(salva);
    }

    public List<FazendaResponse> listar() {
        return fazendaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public FazendaResponse buscarPorId(Long id) {
        Fazenda fazenda = buscarEntidadePorId(id);
        return toResponse(fazenda);
    }

    public FazendaResponse atualizar(Long id, FazendaRequest request) {
        Fazenda fazenda = buscarEntidadePorId(id);

        fazenda.setNome(request.nome());
        fazenda.setLocalizacao(request.localizacao());
        fazenda.setResponsavel(request.responsavel());

        Fazenda atualizada = fazendaRepository.save(fazenda);
        return toResponse(atualizada);
    }

    public void deletar(Long id) {
        Fazenda fazenda = buscarEntidadePorId(id);
        fazendaRepository.delete(fazenda);
    }

    public Fazenda buscarEntidadePorId(Long id) {
        return fazendaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Fazenda não encontrada"));    }

    private FazendaResponse toResponse(Fazenda fazenda) {
        return new FazendaResponse(
                fazenda.getId(),
                fazenda.getNome(),
                fazenda.getLocalizacao(),
                fazenda.getResponsavel()
        );
    }
}