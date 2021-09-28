package com.zup.proposta.analise;

import com.zup.proposta.config.validator.UniqueValue;
import com.zup.proposta.config.validator.ValidaDocumento;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.text.Normalizer;

public class PropostaRequest {
    /** To-Do/
     * Precisa fazer uma limpa nos números de documento
     * pois estão passando se muda a máscara.
     */
    @NotBlank
    private String nome;
    @ValidaDocumento
    @NotBlank
    private String documento;
    @Email @NotBlank
    @UniqueValue(domainClass = Proposta.class, fieldName = "email", message = "Esse e-mail já está cadastrado")
    private String email;
    @PositiveOrZero
    @NotNull
    private BigDecimal salario = new BigDecimal(0);
    @NotBlank
    private String endereco;

    public PropostaRequest(String nome,String documento, String email, BigDecimal salario, String endereco) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Proposta toModel() {
        limpaMascara(documento);
        Proposta proposta = new Proposta(nome, documento, email, salario, endereco);
        return proposta;
    }


    public void limpaMascara(String str) {
        String strNormal = Normalizer.normalize(str, Normalizer.Form.NFD);
        strNormal = strNormal.replaceAll("[/.-]", "");
        documento = strNormal;
    }
}
