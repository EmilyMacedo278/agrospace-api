package br.com.fiap.agrospace.service;

import br.com.fiap.agrospace.dto.request.LeituraAmbientalRequest;
import br.com.fiap.agrospace.dto.response.LeituraAmbientalResponse;
import br.com.fiap.agrospace.entity.AreaAgricola;
import br.com.fiap.agrospace.entity.LeituraAmbiental;
import br.com.fiap.agrospace.entity.Satelite;
import br.com.fiap.agrospace.exception.ResourceNotFoundException;
import br.com.fiap.agrospace.repository.LeituraAmbientalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LeituraAmbientalService {

    private final LeituraAmbientalRepository leituraAmbientalRepository;
    private final AreaAgricolaService areaAgricolaService;
    private final SateliteService sateliteService;

    @CacheEvict(value = {"leiturasAmbientais", "leituraAmbiental"}, allEntries = true)
    public LeituraAmbientalResponse criar(LeituraAmbientalRequest request) {
        AreaAgricola area = areaAgricolaService.buscarEntidadePorId(request.areaAgricolaId());
        Satelite satelite = sateliteService.buscarEntidadePorId(request.sateliteId());

        LeituraAmbiental leitura = LeituraAmbiental.builder()
                .temperatura(request.temperatura())
                .umidade(request.umidade())
                .indiceVegetacao(request.indiceVegetacao())
                .nivelRisco(calcularNivelRisco(request.temperatura(), request.umidade(), request.indiceVegetacao()))
                .dataLeitura(LocalDateTime.now())
                .areaAgricola(area)
                .satelite(satelite)
                .build();

        LeituraAmbiental salva = leituraAmbientalRepository.save(leitura);
        return toResponse(salva);
    }

    @Cacheable(value = "leiturasAmbientais")
    public Page<LeituraAmbientalResponse> listar(Pageable pageable) {
        return leituraAmbientalRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Cacheable(value = "leituraAmbiental", key = "#id")
    public LeituraAmbientalResponse buscarPorId(Long id) {
        LeituraAmbiental leitura = buscarEntidadePorId(id);
        return toResponse(leitura);
    }

    @CacheEvict(value = {"leiturasAmbientais", "leituraAmbiental"}, allEntries = true)
    public LeituraAmbientalResponse atualizar(Long id, LeituraAmbientalRequest request) {
        LeituraAmbiental leitura = buscarEntidadePorId(id);
        AreaAgricola area = areaAgricolaService.buscarEntidadePorId(request.areaAgricolaId());
        Satelite satelite = sateliteService.buscarEntidadePorId(request.sateliteId());

        leitura.setTemperatura(request.temperatura());
        leitura.setUmidade(request.umidade());
        leitura.setIndiceVegetacao(request.indiceVegetacao());
        leitura.setNivelRisco(calcularNivelRisco(request.temperatura(), request.umidade(), request.indiceVegetacao()));
        leitura.setAreaAgricola(area);
        leitura.setSatelite(satelite);

        LeituraAmbiental atualizada = leituraAmbientalRepository.save(leitura);
        return toResponse(atualizada);
    }

    @CacheEvict(value = {"leiturasAmbientais", "leituraAmbiental"}, allEntries = true)
    public void deletar(Long id) {
        LeituraAmbiental leitura = buscarEntidadePorId(id);
        leituraAmbientalRepository.delete(leitura);
    }

    public LeituraAmbiental buscarEntidadePorId(Long id) {
        return leituraAmbientalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leitura ambiental não encontrada"));
    }

    private String calcularNivelRisco(Double temperatura, Double umidade, Double indiceVegetacao) {
        if (temperatura >= 38 || umidade <= 25 || indiceVegetacao <= 0.3) {
            return "ALTO";
        }

        if (temperatura >= 32 || umidade <= 40 || indiceVegetacao <= 0.5) {
            return "MEDIO";
        }

        return "BAIXO";
    }

    private LeituraAmbientalResponse toResponse(LeituraAmbiental leitura) {
        return new LeituraAmbientalResponse(
                leitura.getId(),
                leitura.getTemperatura(),
                leitura.getUmidade(),
                leitura.getIndiceVegetacao(),
                leitura.getNivelRisco(),
                leitura.getDataLeitura(),
                leitura.getAreaAgricola().getId(),
                leitura.getAreaAgricola().getNome(),
                leitura.getSatelite().getId(),
                leitura.getSatelite().getNome()
        );
    }
}