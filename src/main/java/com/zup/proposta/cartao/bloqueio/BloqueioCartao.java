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
    private AvisoBloqueioCartaoStatus statusCartao = AvisoBloqueioCartaoStatus.BLOQUEADO;
    private LocalDateTime instanteOperacao = LocalDateTime.now();
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public BloqueioCartao() {
    }


    public BloqueioCartao(String ip, String userAgent, Cartao cartao) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public AvisoBloqueioCartaoStatus getStatusCartao() {
        return statusCartao;
    }
}
