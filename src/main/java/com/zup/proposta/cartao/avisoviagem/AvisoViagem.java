package com.zup.proposta.cartao.avisoviagem;

import com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destino;

    @NotNull
    private LocalDate terminoViagem;

    private String ip;

    private String userAgent;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    public Cartao getCartao() {
        return cartao;
    }

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(String destino, LocalDate terminoViagem, String ip, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.terminoViagem = terminoViagem;
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }
}

