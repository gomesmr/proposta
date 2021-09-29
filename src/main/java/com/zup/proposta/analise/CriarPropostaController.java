package com.zup.proposta.analise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity novaProposta(@RequestBody @Valid PropostaRequest request) throws SQLIntegrityConstraintViolationException, JsonProcessingException {
        Proposta novaProposta = request.toModel();

        if(!repository.findByDocumento(novaProposta.getDocumento()).isEmpty()){
            throw new SQLIntegrityConstraintViolationException("Esse documento já consta no sistema");
        }
        novaProposta = repository.save(novaProposta);

        //Consultar API para saber se é elegível
        if (isElegivel(novaProposta)){
            novaProposta.setStatusProposta(StatusProposta.ELEGIVEL);
        }else {
            novaProposta.setStatusProposta(StatusProposta.NAO_ELEGIVEL);
        }
        //Update
        //novaProposta = repository.save(novaProposta);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaProposta.getId())
                .toUri();
        return ResponseEntity.created(location).build();


    }

    public boolean isElegivel(Proposta novaProposta) throws JsonProcessingException {
        //preparar o corpo do request
        ElegibilidadePropostaResponse response = null;
        ElegibilidadePropostaRequest body = new ElegibilidadePropostaRequest(novaProposta.getDocumento(), novaProposta.getNome(), String.valueOf(novaProposta.getId()));
        try {
            response = analisePropostaClient.verificarElegibilidade(body);
        } catch (FeignException.UnprocessableEntity ex) {
            String responseBody = ex.getMessage().substring(ex.getMessage().indexOf("{"), ex.getMessage().lastIndexOf("}")+1);
            Gson gson = new Gson();
            response = gson.fromJson(responseBody, ElegibilidadePropostaResponse.class);
        }
        return response.isElegigel();
    }
}
