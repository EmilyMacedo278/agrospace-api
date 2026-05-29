package br.com.fiap.agrospace.service;

import br.com.fiap.agrospace.dto.request.FazendaRequest;
import br.com.fiap.agrospace.dto.response.FazendaResponse;
import br.com.fiap.agrospace.entity.Fazenda;
import br.com.fiap.agrospace.entity.Localizacao;
import br.com.fiap.agrospace.exception.ResourceNotFoundException;
import br.com.fiap.agrospace.repository.FazendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FazendaService {

    private final FazendaRepository fazendaRepository;

    @CacheEvict(value = {"fazendas", "fazenda"}, allEntries = true)
    public FazendaResponse criar(FazendaRequest request) {
        Fazenda fazenda = Fazenda.builder()
                .nome(request.nome())
                .localizacao(new Localizacao(
                        request.cidade(),
                        request.estado(),
                        request.pais()
                ))
                .responsavel(request.responsavel())
                .build();

        Fazenda salva = fazendaRepository.save(fazenda);
        return toResponse(salva);
    }

    @Cacheable(value = "fazendas")
    public Page<FazendaResponse> listar(Pageable pageable) {
        return fazendaRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Cacheable(value = "fazenda", key = "#id")
    public FazendaResponse buscarPorId(Long id) {
        Fazenda fazenda = buscarEntidadePorId(id);
        return toResponse(fazenda);
    }

    @CacheEvict(value = {"fazendas", "fazenda"}, allEntries = true)
    public FazendaResponse atualizar(Long id, FazendaRequest request) {
        Fazenda fazenda = buscarEntidadePorId(id);

        fazenda.setNome(request.nome());
        fazenda.setLocalizacao(new Localizacao(
                request.cidade(),
                request.estado(),
                request.pais()
        ));
        fazenda.setResponsavel(request.responsavel());

        Fazenda atualizada = fazendaRepository.save(fazenda);
        return toResponse(atualizada);
    }

    @CacheEvict(value = {"fazendas", "fazenda"}, allEntries = true)
    public void deletar(Long id) {
        Fazenda fazenda = buscarEntidadePorId(id);
        fazendaRepository.delete(fazenda);
    }

    public Fazenda buscarEntidadePorId(Long id) {
        return fazendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fazenda não encontrada"));
    }

    private FazendaResponse toResponse(Fazenda fazenda) {
        return new FazendaResponse(
                fazenda.getId(),
                fazenda.getNome(),
                fazenda.getLocalizacao().getCidade(),
                fazenda.getLocalizacao().getEstado(),
                fazenda.getLocalizacao().getPais(),
                fazenda.getResponsavel()
        );
    }
}