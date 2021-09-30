package com.zup.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoResponse {

    private Long id;

    private String numero;

    private LocalDateTime emitidoEm;

    private BigDecimal limite;

    public CartaoResponse(Cartao cartao) {
        this.id = cartao.getId();
        this.numero = cartao.getNumero();
        this.emitidoEm = cartao.getDataEmissao();
        this.limite = cartao.getLimite();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public void setEmititdoEm(LocalDateTime emititdoEm) {
        this.emitidoEm = emititdoEm;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
}
