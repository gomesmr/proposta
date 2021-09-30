package com.zup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "associacao", url = "${feign.client.associacao.url}")
public interface ConsultarCartaoClient {

    @PostMapping(value = "/api/cartoes")
    AssociarCartaoResponse associarCartaoProposta (AssociarCartaoRequest request);

}
