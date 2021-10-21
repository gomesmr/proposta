package com.zup.proposta.cartao.bloqueio;

import com.zup.proposta.cartao.Cartao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class BloqeioCartaoRequest {
    @NotBlank
    private String ip;
    @NotBlank
    private String userAgent;


    public BloqeioCartaoRequest(String ip, String userAgent) {
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public BloqueioCartao toModel(@Valid BloqeioCartaoRequest request, Cartao cartao){
        return new BloqueioCartao(this.ip, this.userAgent, cartao);
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
