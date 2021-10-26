package com.zup.proposta.carteira;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.cartao.CartaoRepository;
import com.zup.proposta.cartao.ConsultarCartaoClient;
import com.zup.proposta.config.validator.CustomBusinessRuleViolation;
import com.zup.proposta.config.validator.CustomNotFoundException;
import com.zup.proposta.config.validator.CustomServerErrorException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.net.URI;

@RestController
@RequestMapping(value = "/cartoes")
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    ConsultarCartaoClient consultarCartaoClient;
    @Autowired
    private CarteiraRepository carteiraRepository;

    @PostMapping(value = "/{numeroCartao}/carteira")
    public ResponseEntity associaCarteira(@PathVariable("numeroCartao") String numeroCartao,
                                  @RequestBody CarteiraRequest request,
                                  @AuthenticationPrincipal Jwt jwt,
                                  ServletUriComponentsBuilder builder) {

        Cartao cartao = cartaoRepository.findByNumero(numeroCartao).orElseThrow(() ->
                new CustomNotFoundException("numeroCartao", "Este cartão não consta no sistema"));

        String emailUsuarioLogado = (String) jwt.getClaims().get("email");

        if (!request.getEmail().equals(emailUsuarioLogado)) {
            throw new CustomBusinessRuleViolation("email", "Este email não é valido para essa associação");
        }

        //Verifica se este cartão já foi associado a esta carteira
        if (cartao.verificaCartaoContemCarteira(request.getCarteiraTipo())) {
            throw new CustomBusinessRuleViolation("cartao", "Essa associação já está ativa no sistema");
        }

        try {
            CarteiraAssociadaResponse response = consultarCartaoClient.associaCarteiraCartao(cartao.getNumero(), request);
        if (!response.getResultado().equals(CarteiraResultado.ASSOCIADA)){
            throw new CustomBusinessRuleViolation("resultado", "Erro ao associar a carteira");
        }
        }catch (FeignException feignException){
            throw new CustomServerErrorException("erro", "feign exception");
        }


        Carteira carteira = request.toModel(cartao);

        Carteira carteiraCriada = carteiraRepository.save(carteira);

        URI uri = builder.path("/cartoes/{id}/carteira")
                .buildAndExpand(carteiraCriada.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
