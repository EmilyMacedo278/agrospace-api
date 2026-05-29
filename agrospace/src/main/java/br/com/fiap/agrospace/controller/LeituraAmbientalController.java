package br.com.fiap.agrospace.controller;

import br.com.fiap.agrospace.dto.request.LeituraAmbientalRequest;
import br.com.fiap.agrospace.dto.response.LeituraAmbientalResponse;
import br.com.fiap.agrospace.service.LeituraAmbientalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leituras-ambientais")
@RequiredArgsConstructor
public class LeituraAmbientalController {

    private final LeituraAmbientalService leituraAmbientalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeituraAmbientalResponse criar(@RequestBody @Valid LeituraAmbientalRequest request) {
        return leituraAmbientalService.criar(request);
    }

    @GetMapping
    public List<LeituraAmbientalResponse> listar() {
        return leituraAmbientalService.listar();
    }

    @GetMapping("/{id}")
    public LeituraAmbientalResponse buscarPorId(@PathVariable Long id) {
        return leituraAmbientalService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LeituraAmbientalResponse atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LeituraAmbientalRequest request
    ) {
        return leituraAmbientalService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        leituraAmbientalService.deletar(id);
    }
}