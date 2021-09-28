package com.zup.proposta.analise;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping(value = "/propostas")
public class CriarPropostaController {

    @Autowired
    PropostaRepository repository;
    @Autowired
    AnalisePropostaClient analisePropostaClient;

    @PostMapping
    @Transactional
    public ResponseEntity novaProposta(@RequestBody @Valid PropostaRequest request) throws SQLIntegrityConstraintViolationException {
        Proposta novaProposta = request.toModel();

        if(!repository.findByDocumento(novaProposta.getDocumento()).isEmpty()){
            throw new SQLIntegrityConstraintViolationException("Azeitou o SQL");
        }
        novaProposta = repository.save(novaProposta);

        //Consultar API para saber se é elegível
        if (isElegivel(novaProposta)){
            novaProposta.setStatusProposta(StatusProposta.ELEGIVEL);
        }else {
            novaProposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
        }
        //Update
        novaProposta = repository.save(novaProposta);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaProposta.getId())
                .toUri();
        return ResponseEntity.created(location).build();


    }

    public boolean isElegivel(Proposta novaProposta) {
        //preparar o corpo do request
        ElegibilidadePropostaRequest body = new ElegibilidadePropostaRequest(novaProposta.getDocumento(), novaProposta.getNome(), String.valueOf(novaProposta.getId()));
            ElegibilidadePropostaResponse response = analisePropostaClient.verificarElegibilidade(body);
            return (response.getResultadoSolicitacao().equals("SEM_RESTRICAO"));


    }
}
