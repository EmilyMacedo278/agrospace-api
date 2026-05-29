package br.com.fiap.agrospace.service;

import br.com.fiap.agrospace.dto.request.SateliteRequest;
import br.com.fiap.agrospace.dto.response.SateliteResponse;
import br.com.fiap.agrospace.entity.Satelite;
import br.com.fiap.agrospace.exception.ResourceNotFoundException;
import br.com.fiap.agrospace.repository.SateliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SateliteService {

    private final SateliteRepository sateliteRepository;

    @CacheEvict(value = {"satelites", "satelite"}, allEntries = true)
    public SateliteResponse criar(SateliteRequest request) {
        Satelite satelite = Satelite.builder()
                .nome(request.nome())
                .tipoMonitoramento(request.tipoMonitoramento())
                .status(request.status())
                .build();

        Satelite salvo = sateliteRepository.save(satelite);
        return toResponse(salvo);
    }

    @Cacheable(value = "satelites")
    public Page<SateliteResponse> listar(Pageable pageable) {
        return sateliteRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Cacheable(value = "satelite", key = "#id")
    public SateliteResponse buscarPorId(Long id) {
        Satelite satelite = buscarEntidadePorId(id);
        return toResponse(satelite);
    }

    @CacheEvict(value = {"satelites", "satelite"}, allEntries = true)
    public SateliteResponse atualizar(Long id, SateliteRequest request) {
        Satelite satelite = buscarEntidadePorId(id);

        satelite.setNome(request.nome());
        satelite.setTipoMonitoramento(request.tipoMonitoramento());
        satelite.setStatus(request.status());

        Satelite atualizado = sateliteRepository.save(satelite);
        return toResponse(atualizado);
    }

    @CacheEvict(value = {"satelites", "satelite"}, allEntries = true)
    public void deletar(Long id) {
        Satelite satelite = buscarEntidadePorId(id);
        sateliteRepository.delete(satelite);
    }

    public Satelite buscarEntidadePorId(Long id) {
        return sateliteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Satélite não encontrado"));
    }

    private SateliteResponse toResponse(Satelite satelite) {
        return new SateliteResponse(
                satelite.getId(),
                satelite.getNome(),
                satelite.getTipoMonitoramento(),
                satelite.getStatus()
        );
    }
}