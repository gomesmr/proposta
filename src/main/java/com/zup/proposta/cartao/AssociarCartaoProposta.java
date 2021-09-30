package com.zup.proposta.cartao;

import com.zup.proposta.analise.PropostaRepository;
import com.zup.proposta.analise.StatusProposta;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AssociarCartaoProposta {
    private final PropostaRepository propostaRepository;
    private final ConsultarCartaoClient consultarCartaoClient;

    public AssociarCartaoProposta(PropostaRepository propostaRepository, ConsultarCartaoClient consultarCartaoClient) {
        this.propostaRepository = propostaRepository;
        this.consultarCartaoClient = consultarCartaoClient;
    }

    @Scheduled(fixedDelayString = "${associar.cartao.proposta.time.schedule}")
    public void associa() {
        var propostas = propostaRepository.findByStatusPropostaAndCartaoIsNull(StatusProposta.ELEGIVEL);

        propostas.forEach(proposta -> {
            AssociarCartaoRequest request;
            request = new AssociarCartaoRequest(proposta);
            try {
                var response = consultarCartaoClient.associarCartaoProposta(request);
                var cartao = response.toModel(proposta);
                proposta.adicionarCartaoProposta(cartao);
                propostaRepository.save(proposta);
            } catch (FeignException exception) {
                exception.printStackTrace();
            }
        });
    }
}
