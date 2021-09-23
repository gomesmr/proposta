package com.zup.proposta.analise;

import com.zup.proposta.config.validator.ValidaDocumento;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, BigDecimal salario, String endereco) {
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }
}
