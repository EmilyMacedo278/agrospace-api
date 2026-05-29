package br.com.fiap.agrospace.controller;

import br.com.fiap.agrospace.dto.request.AreaAgricolaRequest;
import br.com.fiap.agrospace.dto.response.AreaAgricolaResponse;
import br.com.fiap.agrospace.service.AreaAgricolaService;
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
@RequestMapping("/areas-agricolas")
@RequiredArgsConstructor
public class AreaAgricolaController {

    private final AreaAgricolaService areaAgricolaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<AreaAgricolaResponse> criar(@RequestBody @Valid AreaAgricolaRequest request) {
        AreaAgricolaResponse response = areaAgricolaService.criar(request);
        return adicionarLinks(response);
    }

    @GetMapping
    public Page<EntityModel<AreaAgricolaResponse>> listar(Pageable pageable) {
        return areaAgricolaService.listar(pageable)
                .map(this::adicionarLinks);
    }

    @GetMapping("/{id}")
    public EntityModel<AreaAgricolaResponse> buscarPorId(@PathVariable Long id) {
        AreaAgricolaResponse response = areaAgricolaService.buscarPorId(id);
        return adicionarLinks(response);
    }

    @PutMapping("/{id}")
    public EntityModel<AreaAgricolaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AreaAgricolaRequest request
    ) {
        AreaAgricolaResponse response = areaAgricolaService.atualizar(id, request);
        return adicionarLinks(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        areaAgricolaService.deletar(id);
    }

    private EntityModel<AreaAgricolaResponse> adicionarLinks(AreaAgricolaResponse response) {
        return EntityModel.of(
                response,
                linkTo(methodOn(AreaAgricolaController.class).buscarPorId(response.id())).withSelfRel(),
                linkTo(methodOn(AreaAgricolaController.class).listar(null)).withRel("todas-as-areas-agricolas"),
                linkTo(methodOn(FazendaController.class).buscarPorId(response.fazendaId())).withRel("fazenda")
        );
    }
}