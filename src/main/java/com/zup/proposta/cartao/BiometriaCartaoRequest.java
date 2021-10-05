package com.zup.proposta.cartao;

import javax.validation.constraints.NotBlank;


public class BiometriaCartaoRequest {
    @NotBlank
    private String fingerprint;

    public BiometriaCartaoRequest(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    @Deprecated
    public BiometriaCartaoRequest() {
    }


    public BiometriaCartao toModel(Cartao cartao) {
        BiometriaCartao novaBiometria = new BiometriaCartao(this.fingerprint, cartao);
        return novaBiometria;

    }

    public String getFingerprint() {
        return fingerprint;
    }
}
