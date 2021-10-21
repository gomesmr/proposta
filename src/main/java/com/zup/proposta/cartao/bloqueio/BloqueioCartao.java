package com.zup.proposta.cartao.bloqueio;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.cartao.biometria.BiometriaCartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Iterator;

@Entity
public class BloqueioCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String ip;
    @NotBlank
    private String userAgent;
    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao = StatusCartao.BLOQUEADO;
    private LocalDateTime instanteOperacao = LocalDateTime.now();
    @ManyToOne
    private Cartao cartao;

    public BloqueioCartao() {
    }

    @Deprecated


    public BloqueioCartao(String ip, String userAgent, Cartao cartao) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "BloqueioCartao{" +
                ", ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", statusCartao=" + statusCartao +
                ", instanteOperacao=" + instanteOperacao +
                ", cartao=" + cartao +
                '}';
    }

    public StatusCartao getStatusCartao() {
        return statusCartao;
    }
}
