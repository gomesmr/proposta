package com.zup.proposta.cartao.bloqueio;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.cartao.CartaoRepository;
import com.zup.proposta.config.validator.CustomBusinessRuleViolation;
import com.zup.proposta.config.validator.CustomNotFoundException;
import com.zup.proposta.config.validator.CustomServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cartoes")
public class BloqueioCartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BloqueioCartaoRepository bloqueioCartaoRepository;
    @Autowired
    private BloqueioCartaoClient bloqueioCartaoClient;

    @PostMapping(value = "/{id}/bloqueio")
    public String bloquearCartao(@PathVariable("id") String numero, @RequestBody @Valid BloqeioCartaoRequest request, @AuthenticationPrincipal Jwt jwt){

        System.out.println(jwt);
        Cartao cartao = cartaoRepository.findByNumero(numero).orElseThrow(()->
                new CustomNotFoundException("numero", "Cartão não localizado no sistema"));

        String documentoUsuarioLogado = (String) jwt.getClaims().get("documento");

        //verifica se cartão está bloqueado
        var cartaoPertenceAoUsuarioLogado = cartao.getProposta().getDocumento().equals(documentoUsuarioLogado);

        if (!cartaoPertenceAoUsuarioLogado) {
            throw new CustomBusinessRuleViolation("cartao", "Cartão informado não pertence a esse usuário.");
        }

        if(cartao.cartaoJaEstaBloqueado()){
            throw new CustomBusinessRuleViolation("cartao", "Cartão já está bloqueado.");
        }

        //verificar se o sistema legado realizou o bloqueio
        if(!avisaBloqueio(numero)){
            throw new CustomServerErrorException("cartao", "Seu cartão NÃO foi bloqueado! Tente novamente.");
        }

        BloqueioCartao bloqueio = request.toModel(request, cartao);
        bloqueioCartaoRepository.save(bloqueio);
        return "cartão bloqueado com sucesso";

    }

    private boolean avisaBloqueio (String numeroCartao){
        AvisoBloqueioCartaoRequest avisoBloqueio = new AvisoBloqueioCartaoRequest("marcelosOnTheBlock");

        AvisoBloqueioCartaoResponse avisoBloqueioCartaoResponse = bloqueioCartaoClient.avisarSistemalegado(numeroCartao, avisoBloqueio);

        if(avisoBloqueioCartaoResponse.getResultado().equals("BLOQUEADO"))
            return true;
        return false;
    }
}
