package br.com.fiap.agrospace.controller;

import br.com.fiap.agrospace.dto.request.AreaAgricolaRequest;
import br.com.fiap.agrospace.dto.response.AreaAgricolaResponse;
import br.com.fiap.agrospace.service.AreaAgricolaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas-agricolas")
@RequiredArgsConstructor
public class AreaAgricolaController {

    private final AreaAgricolaService areaAgricolaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaAgricolaResponse criar(@RequestBody @Valid AreaAgricolaRequest request) {
        return areaAgricolaService.criar(request);
    }

    @GetMapping
    public List<AreaAgricolaResponse> listar() {
        return areaAgricolaService.listar();
    }

    @GetMapping("/{id}")
    public AreaAgricolaResponse buscarPorId(@PathVariable Long id) {
        return areaAgricolaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AreaAgricolaResponse atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AreaAgricolaRequest request
    ) {
        return areaAgricolaService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        areaAgricolaService.deletar(id);
    }
}