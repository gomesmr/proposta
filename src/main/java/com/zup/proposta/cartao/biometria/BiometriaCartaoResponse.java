package com.zup.proposta.cartao.biometria;

import java.time.LocalDateTime;

public class BiometriaCartaoResponse {
    private Long id;
    private LocalDateTime dataCadastroBiometria;

    public BiometriaCartaoResponse(BiometriaCartao biometriaCartao) {
        this.id = biometriaCartao.getId();
        this.dataCadastroBiometria = biometriaCartao.getDataCadastroBiometria();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCadastroBiometria() {
        return dataCadastroBiometria;
    }
}






