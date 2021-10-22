package com.zup.proposta.cartao.avisoviagem;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.cartao.CartaoRepository;
import com.zup.proposta.cartao.NotificaAvisoViagemResponse;
import com.zup.proposta.config.validator.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cartoes")
public class AvisoViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private NotificaAvisoViagem notificaAvisoViagem;

    @PostMapping("/{numeroCartao}/viagem")
    @Transactional
    public void save(@PathVariable String numeroCartao, @RequestBody @Valid AvisoViagemRequest avisoViagemRequest,
                     HttpServletRequest request) {
        Cartao cartao = cartaoRepository.findByNumero(numeroCartao)
                .orElseThrow(() -> new CustomNotFoundException("cartao", "Cartao não foi localizado"));

        String remoteAddress = request.getRemoteAddr();
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

        AvisoViagem avisoViagem = avisoViagemRequest.toModel(remoteAddress, userAgent, cartao);


        NotificaAvisoViagemResponse responseNotificao = notificaAvisoViagem.processar(cartao.getNumero(), avisoViagem);

        if (responseNotificao.getResultado()==StatusAvisoViagem.CRIADO) {
            cartao.adicionaAviso(avisoViagem);
            cartaoRepository.save(cartao);
        }
    }
}
