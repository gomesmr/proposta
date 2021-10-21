package com.zup.proposta.cartao.bloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "api-bloqueia-cartao", url = "http://localhost:8888/api/cartoes")
public interface BloqueioCartaoClient {
    @PostMapping("/{id}/bloqueios")
    public AvisoBloqueioCartaoResponse avisarSistemalegado(@PathVariable("id") String id,
                                                           AvisoBloqueioCartaoRequest bloqueioCartaoAvisoRequest);
}
