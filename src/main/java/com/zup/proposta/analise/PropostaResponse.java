package com.zup.proposta.analise;

import com.zup.proposta.cartao.CartaoResponse;

import java.math.BigDecimal;
import java.util.Objects;

public class PropostaResponse {

    private Long id;

    private String documento;

    private String email;

    private String nome;

    private String endereco;

    private BigDecimal salario;

    private StatusProposta statusProposta;

    private CartaoResponse cartao;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.statusProposta = proposta.getStatusProposta();
        if (Objects.nonNull(proposta.getCartao())) {
            this.cartao = new CartaoResponse(proposta.getCartao());
        }
    }

    public Long getId() {
        return id;
    }
    public String getDocumento() {
        return documento;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public BigDecimal getSalario() {
        return salario;
    }
    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
    public CartaoResponse getCartao() {
        return cartao;
    }
}
