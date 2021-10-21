package com.zup.proposta.cartao.bloqueio;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AvisoBloqueioCartaoResponse {

    private String resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoBloqueioCartaoResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


}
