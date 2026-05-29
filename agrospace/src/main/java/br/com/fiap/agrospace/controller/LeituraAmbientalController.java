package br.com.fiap.agrospace.controller;

import br.com.fiap.agrospace.dto.request.LeituraAmbientalRequest;
import br.com.fiap.agrospace.dto.response.LeituraAmbientalResponse;
import br.com.fiap.agrospace.service.LeituraAmbientalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/leituras-ambientais")
@RequiredArgsConstructor
public class LeituraAmbientalController {

    private final LeituraAmbientalService leituraAmbientalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<LeituraAmbientalResponse> criar(@RequestBody @Valid LeituraAmbientalRequest request) {
        LeituraAmbientalResponse response = leituraAmbientalService.criar(request);
        return adicionarLinks(response);
    }

    @GetMapping
    public Page<EntityModel<LeituraAmbientalResponse>> listar(Pageable pageable) {
        return leituraAmbientalService.listar(pageable)
                .map(this::adicionarLinks);
    }

    @GetMapping("/{id}")
    public EntityModel<LeituraAmbientalResponse> buscarPorId(@PathVariable Long id) {
        LeituraAmbientalResponse response = leituraAmbientalService.buscarPorId(id);
        return adicionarLinks(response);
    }

    @PutMapping("/{id}")
    public EntityModel<LeituraAmbientalResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LeituraAmbientalRequest request
    ) {
        LeituraAmbientalResponse response = leituraAmbientalService.atualizar(id, request);
        return adicionarLinks(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        leituraAmbientalService.deletar(id);
    }

    private EntityModel<LeituraAmbientalResponse> adicionarLinks(LeituraAmbientalResponse response) {
        return EntityModel.of(
                response,
                linkTo(methodOn(LeituraAmbientalController.class).buscarPorId(response.id())).withSelfRel(),
                linkTo(methodOn(LeituraAmbientalController.class).listar(null)).withRel("todas-as-leituras"),
                linkTo(methodOn(AreaAgricolaController.class).buscarPorId(response.areaAgricolaId())).withRel("area-agricola"),
                linkTo(methodOn(SateliteController.class).buscarPorId(response.sateliteId())).withRel("satelite")
        );
    }
}