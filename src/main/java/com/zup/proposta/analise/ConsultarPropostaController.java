package com.zup.proposta.analise;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propostas")
public class ConsultarPropostaController {

    private final PropostaRepository propostaRepository;
    private final Tracer tracer;

    public ConsultarPropostaController(PropostaRepository propostaRepository, Tracer tracer) {
        this.propostaRepository = propostaRepository;
        this.tracer = tracer;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id,
                                      @AuthenticationPrincipal Jwt jwt) {
        String emailUsuarioLogado = (String) jwt.getClaims().get("email");

        Span activeSpan = tracer.activeSpan();
        activeSpan.setBaggageItem("user.email", emailUsuarioLogado);

        return propostaRepository.findById(id)
                .map(PropostaResponse::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
