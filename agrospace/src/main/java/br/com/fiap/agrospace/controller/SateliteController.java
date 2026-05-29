package br.com.fiap.agrospace.controller;

import br.com.fiap.agrospace.dto.request.SateliteRequest;
import br.com.fiap.agrospace.dto.response.SateliteResponse;
import br.com.fiap.agrospace.service.SateliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/satelites")
@RequiredArgsConstructor
public class SateliteController {

    private final SateliteService sateliteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SateliteResponse criar(@RequestBody @Valid SateliteRequest request) {
        return sateliteService.criar(request);
    }

    @GetMapping
    public List<SateliteResponse> listar() {
        return sateliteService.listar();
    }

    @GetMapping("/{id}")
    public SateliteResponse buscarPorId(@PathVariable Long id) {
        return sateliteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public SateliteResponse atualizar(
            @PathVariable Long id,
            @RequestBody @Valid SateliteRequest request
    ) {
        return sateliteService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        sateliteService.deletar(id);
    }
}