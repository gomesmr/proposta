package com.zup.proposta.cartao;


import com.zup.proposta.cartao.avisoviagem.StatusAvisoViagem;

public class NotificaAvisoViagemResponse {

    private StatusAvisoViagem resultado;

    public StatusAvisoViagem getResultado() {
        return resultado;
    }

    public void setResultado(StatusAvisoViagem resultado) {
        this.resultado = resultado;
    }
}
