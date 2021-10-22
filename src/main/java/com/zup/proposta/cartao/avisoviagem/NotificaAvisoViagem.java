package com.zup.proposta.cartao.avisoviagem;

import com.zup.proposta.cartao.ConsultarCartaoClient;
import com.zup.proposta.cartao.NotificaAvisoViagemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
@Service
public class NotificaAvisoViagem {

    @Autowired
    private ConsultarCartaoClient consultarCartaoClient;

    public NotificaAvisoViagemResponse processar(String numeroCartao, AvisoViagem avisoViagem) {
        String destino = avisoViagem.getDestino();
        String validoAte = avisoViagem.getTerminoViagem().format(DateTimeFormatter.ofPattern("2021-09-23"));
        NotificaAvisoViagemRequest request = new NotificaAvisoViagemRequest(destino, validoAte);
        return consultarCartaoClient.notificarViagem(numeroCartao, request);
    }
}
