package br.com.fiap.agrospace.service;

import br.com.fiap.agrospace.dto.request.SateliteRequest;
import br.com.fiap.agrospace.dto.response.SateliteResponse;
import br.com.fiap.agrospace.entity.Satelite;
import br.com.fiap.agrospace.exception.ResourceNotFoundException;
import br.com.fiap.agrospace.repository.SateliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SateliteService {

    private final SateliteRepository sateliteRepository;

    public SateliteResponse criar(SateliteRequest request) {
        Satelite satelite = Satelite.builder()
                .nome(request.nome())
                .tipoMonitoramento(request.tipoMonitoramento())
                .status(request.status())
                .build();

        Satelite salvo = sateliteRepository.save(satelite);
        return toResponse(salvo);
    }

    public List<SateliteResponse> listar() {
        return sateliteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SateliteResponse buscarPorId(Long id) {
        Satelite satelite = buscarEntidadePorId(id);
        return toResponse(satelite);
    }

    public SateliteResponse atualizar(Long id, SateliteRequest request) {
        Satelite satelite = buscarEntidadePorId(id);

        satelite.setNome(request.nome());
        satelite.setTipoMonitoramento(request.tipoMonitoramento());
        satelite.setStatus(request.status());

        Satelite atualizado = sateliteRepository.save(satelite);
        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        Satelite satelite = buscarEntidadePorId(id);
        sateliteRepository.delete(satelite);
    }

    public Satelite buscarEntidadePorId(Long id) {
        return sateliteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Satélite não encontrado"
));    }

    private SateliteResponse toResponse(Satelite satelite) {
        return new SateliteResponse(
                satelite.getId(),
                satelite.getNome(),
                satelite.getTipoMonitoramento(),
                satelite.getStatus()
        );
    }
}