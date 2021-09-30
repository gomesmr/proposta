package com.zup.proposta.cartao;

import com.zup.proposta.analise.Proposta;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotNull @OneToOne Proposta proposta;
    private @NotBlank String numero;
    private LocalDateTime dataEmissao;
    private @NotNull BigDecimal limite;

    @Deprecated
    public Cartao() {
    }

    public Cartao(Proposta proposta, String numero, LocalDateTime dataEmissao, BigDecimal limite) {
        this.proposta = proposta;
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.limite = limite;
    }

    public Long getId() {
        return id;
    }


    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
