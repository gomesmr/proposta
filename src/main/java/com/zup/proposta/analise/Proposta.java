package com.zup.proposta.analise;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.config.validator.ValidaDocumento;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ValidaDocumento
    @NotBlank
    @Column (unique = true)
    private String documento;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    @PositiveOrZero
    @NotNull
    private BigDecimal salario;
    @NotNull
    private String endereco;
    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;
    @OneToOne(cascade = CascadeType.MERGE, mappedBy = "proposta")
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String nome, String documento, String email, BigDecimal salario, String endereco) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDocumento() {
        return documento;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }


    public void adicionarCartaoProposta(Cartao cartao) {
        this.cartao = cartao;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getEmail() {
        return email;
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
}
