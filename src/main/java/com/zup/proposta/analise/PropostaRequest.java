package com.zup.proposta.analise;

import com.zup.proposta.config.validator.UniqueValue;
import com.zup.proposta.config.validator.ValidaDocumento;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaRequest {
    @ValidaDocumento
    @NotBlank
    @UniqueValue(domainClass = Proposta.class, fieldName = "documento", message = "Esse documento já está cadastrado")
    private String documento;
    @Email @NotBlank
    @UniqueValue(domainClass = Proposta.class, fieldName = "email", message = "Esse e-mail já está cadastrado")
    private String email;
    @PositiveOrZero
    @NotNull
    private BigDecimal salario = new BigDecimal(0);
    @NotBlank
    private String endereco;

    public PropostaRequest(String documento, String email, BigDecimal salario, String endereco) {
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Proposta toModel() {
        Proposta proposta = new Proposta(documento, email, salario, endereco);
        return proposta;
    }
}
