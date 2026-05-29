package br.com.fiap.agrospace.controller;

import br.com.fiap.agrospace.dto.request.FazendaRequest;
import br.com.fiap.agrospace.dto.response.FazendaResponse;
import br.com.fiap.agrospace.service.FazendaService;
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
@RequestMapping("/fazendas")
@RequiredArgsConstructor
public class FazendaController {

    private final FazendaService fazendaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<FazendaResponse> criar(@RequestBody @Valid FazendaRequest request) {
        FazendaResponse response = fazendaService.criar(request);
        return adicionarLinks(response);
    }

    @GetMapping
    public Page<EntityModel<FazendaResponse>> listar(Pageable pageable) {
        return fazendaService.listar(pageable)
                .map(this::adicionarLinks);
    }

    @GetMapping("/{id}")
    public EntityModel<FazendaResponse> buscarPorId(@PathVariable Long id) {
        FazendaResponse response = fazendaService.buscarPorId(id);
        return adicionarLinks(response);
    }

    @PutMapping("/{id}")
    public EntityModel<FazendaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid FazendaRequest request
    ) {
        FazendaResponse response = fazendaService.atualizar(id, request);
        return adicionarLinks(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        fazendaService.deletar(id);
    }

    private EntityModel<FazendaResponse> adicionarLinks(FazendaResponse response) {
        return EntityModel.of(
                response,
                linkTo(methodOn(FazendaController.class).buscarPorId(response.id())).withSelfRel(),
                linkTo(methodOn(FazendaController.class).listar(null)).withRel("todas-as-fazendas")
        );
    }
}