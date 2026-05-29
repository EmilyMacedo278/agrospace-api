package br.com.fiap.agrospace.service;

import br.com.fiap.agrospace.dto.request.AreaAgricolaRequest;
import br.com.fiap.agrospace.dto.response.AreaAgricolaResponse;
import br.com.fiap.agrospace.entity.AreaAgricola;
import br.com.fiap.agrospace.entity.Fazenda;
import br.com.fiap.agrospace.exception.ResourceNotFoundException;
import br.com.fiap.agrospace.repository.AreaAgricolaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AreaAgricolaService {

    private final AreaAgricolaRepository areaAgricolaRepository;
    private final FazendaService fazendaService;

    @CacheEvict(value = {"areasAgricolas", "areaAgricola"}, allEntries = true)
    public AreaAgricolaResponse criar(AreaAgricolaRequest request) {
        Fazenda fazenda = fazendaService.buscarEntidadePorId(request.fazendaId());

        AreaAgricola area = AreaAgricola.builder()
                .nome(request.nome())
                .tipoCultivo(request.tipoCultivo())
                .tamanhoHectares(request.tamanhoHectares())
                .fazenda(fazenda)
                .build();

        AreaAgricola salva = areaAgricolaRepository.save(area);
        return toResponse(salva);
    }

    @Cacheable(value = "areasAgricolas")
    public Page<AreaAgricolaResponse> listar(Pageable pageable) {
        return areaAgricolaRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Cacheable(value = "areaAgricola", key = "#id")
    public AreaAgricolaResponse buscarPorId(Long id) {
        AreaAgricola area = buscarEntidadePorId(id);
        return toResponse(area);
    }

    @CacheEvict(value = {"areasAgricolas", "areaAgricola"}, allEntries = true)
    public AreaAgricolaResponse atualizar(Long id, AreaAgricolaRequest request) {
        AreaAgricola area = buscarEntidadePorId(id);
        Fazenda fazenda = fazendaService.buscarEntidadePorId(request.fazendaId());

        area.setNome(request.nome());
        area.setTipoCultivo(request.tipoCultivo());
        area.setTamanhoHectares(request.tamanhoHectares());
        area.setFazenda(fazenda);

        AreaAgricola atualizada = areaAgricolaRepository.save(area);
        return toResponse(atualizada);
    }

    @CacheEvict(value = {"areasAgricolas", "areaAgricola"}, allEntries = true)
    public void deletar(Long id) {
        AreaAgricola area = buscarEntidadePorId(id);
        areaAgricolaRepository.delete(area);
    }

    public AreaAgricola buscarEntidadePorId(Long id) {
        return areaAgricolaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Área agrícola não encontrada"));
    }

    private AreaAgricolaResponse toResponse(AreaAgricola area) {
        return new AreaAgricolaResponse(
                area.getId(),
                area.getNome(),
                area.getTipoCultivo(),
                area.getTamanhoHectares(),
                area.getFazenda().getId(),
                area.getFazenda().getNome()
        );
    }
}