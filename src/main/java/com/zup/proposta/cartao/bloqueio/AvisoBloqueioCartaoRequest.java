package com.zup.proposta.cartao.bloqueio;

public class AvisoBloqueioCartaoRequest {
    private String sistemaResponsavel;

    public AvisoBloqueioCartaoRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
