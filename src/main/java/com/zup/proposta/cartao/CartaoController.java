package com.zup.proposta.cartao;

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
import java.util.Optional;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        CartaoResponse response = new CartaoResponse(cartao.get());

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
    public ResponseEntity incluirBiometria(@PathVariable Long id, @RequestBody @Valid BiometriaCartaoRequest request) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        BiometriaCartao biometriaCartao = request.toModel(cartao.get());
        biometriaCartao.getCartao().adicionaBiometria(biometriaCartao);

        /**
         * Coverter de Optional para Entidade Modelo
         */
        Cartao cartaoResponse = new Cartao(cartao);
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
