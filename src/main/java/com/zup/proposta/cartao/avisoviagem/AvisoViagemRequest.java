package com.zup.proposta.cartao.avisoviagem;

import com.zup.proposta.cartao.Cartao;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;

    @NotNull
    @FutureOrPresent
    private LocalDate terminoViagem;

    public AvisoViagemRequest(String destino, LocalDate terminoViagem) {
        this.destino = destino;
        this.terminoViagem = terminoViagem;
    }

    public AvisoViagem toModel(String ip, String userAgent, Cartao cartao) {
        return new AvisoViagem(destino, terminoViagem, ip, userAgent, cartao);
    }
}
