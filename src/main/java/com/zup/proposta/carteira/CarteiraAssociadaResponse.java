package com.zup.proposta.carteira;

public class CarteiraAssociadaResponse {
    private String id;
    private CarteiraResultado resultado;

    public CarteiraAssociadaResponse(String id, CarteiraResultado resultado) {
        this.id = id;
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CarteiraResultado getResultado() {
        return resultado;
    }

}
