package br.com.fiap.agrospace.controller;

import br.com.fiap.agrospace.dto.request.SateliteRequest;
import br.com.fiap.agrospace.dto.response.SateliteResponse;
import br.com.fiap.agrospace.service.SateliteService;
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
@RequestMapping("/satelites")
@RequiredArgsConstructor
public class SateliteController {

    private final SateliteService sateliteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<SateliteResponse> criar(@RequestBody @Valid SateliteRequest request) {
        SateliteResponse response = sateliteService.criar(request);
        return adicionarLinks(response);
    }

    @GetMapping
    public Page<EntityModel<SateliteResponse>> listar(Pageable pageable) {
        return sateliteService.listar(pageable)
                .map(this::adicionarLinks);
    }

    @GetMapping("/{id}")
    public EntityModel<SateliteResponse> buscarPorId(@PathVariable Long id) {
        SateliteResponse response = sateliteService.buscarPorId(id);
        return adicionarLinks(response);
    }

    @PutMapping("/{id}")
    public EntityModel<SateliteResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid SateliteRequest request
    ) {
        SateliteResponse response = sateliteService.atualizar(id, request);
        return adicionarLinks(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        sateliteService.deletar(id);
    }

    private EntityModel<SateliteResponse> adicionarLinks(SateliteResponse response) {
        return EntityModel.of(
                response,
                linkTo(methodOn(SateliteController.class).buscarPorId(response.id())).withSelfRel(),
                linkTo(methodOn(SateliteController.class).listar(null)).withRel("todos-os-satelites")
        );
    }
}