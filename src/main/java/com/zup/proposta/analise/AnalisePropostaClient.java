package com.zup.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "elegibilidade", url = "${feign.client.elegibilidade.url}")
public interface AnalisePropostaClient {

    @PostMapping(value = "/api/solicitacao")
    ElegibilidadePropostaResponse verificarElegibilidade (ElegibilidadePropostaRequest request);

}
