package com.zup.proposta.cartao;

import com.zup.proposta.analise.Proposta;

public class AssociarCartaoRequest {
    private String documento;
    private String nome;
    private String idProposta;

    public AssociarCartaoRequest(Proposta proposta){
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }
    public String getNome() {
        return nome;
    }
    public String getIdProposta() {
        return idProposta;
    }
}
