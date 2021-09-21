package com.zup.proposta.analise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/propostas")
public class CriarPropostaController {

    private final PropostaRepository repository;

    public CriarPropostaController(PropostaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity novaProposta(@RequestBody @Valid PropostaRequest request){
        Proposta novaProposta = request.toModel();
        novaProposta = repository.save(novaProposta);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaProposta.getId())
                .toUri();
        return ResponseEntity.created(location).build();


    }
}
