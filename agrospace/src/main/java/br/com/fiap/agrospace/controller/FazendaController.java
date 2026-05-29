package br.com.fiap.agrospace.controller;

import br.com.fiap.agrospace.dto.request.FazendaRequest;
import br.com.fiap.agrospace.dto.response.FazendaResponse;
import br.com.fiap.agrospace.service.FazendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fazendas")
@RequiredArgsConstructor
public class FazendaController {

    private final FazendaService fazendaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FazendaResponse criar(@RequestBody @Valid FazendaRequest request) {
        return fazendaService.criar(request);
    }

    @GetMapping
    public List<FazendaResponse> listar() {
        return fazendaService.listar();
    }

    @GetMapping("/{id}")
    public FazendaResponse buscarPorId(@PathVariable Long id) {
        return fazendaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public FazendaResponse atualizar(
            @PathVariable Long id,
            @RequestBody @Valid FazendaRequest request
    ) {
        return fazendaService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        fazendaService.deletar(id);
    }
}