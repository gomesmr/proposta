package com.zup.proposta.cartao;

import com.zup.proposta.cartao.avisoviagem.NotificaAvisoViagemRequest;
import com.zup.proposta.cartao.bloqueio.AvisoBloqueioCartaoRequest;
import com.zup.proposta.cartao.bloqueio.AvisoBloqueioCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartoes", url = "${feign.client.cartoes.url}")
public interface ConsultarCartaoClient {

    @PostMapping
    AssociarCartaoResponse associarCartaoProposta (AssociarCartaoRequest request);

    @PostMapping("{numeroCartao}/bloqueios")
    public AvisoBloqueioCartaoResponse avisarSistemalegadoBloqueioCartao(@PathVariable("numeroCartao") String numeroCartao,
                                                                         AvisoBloqueioCartaoRequest bloqueioCartaoAvisoRequest);

    @PostMapping("/{numeroCartao}/avisos")
    NotificaAvisoViagemResponse notificarViagem(@PathVariable String numeroCartao, NotificaAvisoViagemRequest request);


}
