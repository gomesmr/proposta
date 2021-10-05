package com.zup.proposta.cartao;

import com.zup.proposta.analise.Proposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssociarCartaoResponse {

    private @NotBlank String id;
    private @NotNull LocalDateTime dataEmissao;
    private @NotNull BigDecimal limite;

    public Cartao toModel(Proposta proposta) {
        return new Cartao(proposta, id, LocalDateTime.now(), limite, null);
    }

    public String getId() {
        return id;
    }
    public LocalDateTime getEmitidoEm() {
        return dataEmissao;
    }
    public BigDecimal getLimite() {
        return limite;
    }
}
