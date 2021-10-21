package com.zup.proposta.cartao.biometria;

import com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class BiometriaCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String biometria;
    private LocalDateTime dataCadastroBiometria = LocalDateTime.now();
    @ManyToOne
    private Cartao cartao;


    @Deprecated
    public BiometriaCartao() {}

    public BiometriaCartao(String biometria, Cartao cartao) {
        this.biometria = biometria;
        this.cartao = cartao;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCadastroBiometria() {
        return dataCadastroBiometria;
    }

}
