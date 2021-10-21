package com.zup.proposta.cartao;

import com.zup.proposta.cartao.biometria.BiometriaCartao;
import com.zup.proposta.cartao.biometria.BiometriaCartaoRequest;
import com.zup.proposta.cartao.biometria.BiometriaCartaoResponse;
import com.zup.proposta.config.validator.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(() ->
                    new CustomNotFoundException("id", "Cartão não foi localizado no sistema"));
        CartaoResponse response = new CartaoResponse(cartao);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * TO-DO Acertar os códigos de retorno quando erro
     * @param id
     * @param request
     * @return
     */
    @PostMapping("/{id}/biometria")
    @Transactional
    public ResponseEntity incluirBiometria(@PathVariable Long id,
                          @RequestBody @Valid BiometriaCartaoRequest request) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(() ->
                new CustomNotFoundException("id", "Cartão não foi localizado"));

        BiometriaCartao biometriaCartao = request.toModel(cartao);
        biometriaCartao.getCartao().adicionaBiometria(biometriaCartao);

        /**
         * Coverter de Optional para Entidade Modelo
         */
        Cartao cartaoResponse = cartao;
        cartaoResponse = entityManager.merge(cartaoResponse);

        /**
         * Acessar a última Biometria Cadastrada
         */
        BiometriaCartao biometriaCriada= cartaoResponse.retornaUltimaBiometria();
        BiometriaCartaoResponse response = new BiometriaCartaoResponse(biometriaCriada);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}
