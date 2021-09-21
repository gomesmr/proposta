package com.zup.proposta.analise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CriarPropostaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PropostaRepository propostaRepository;

    @Test
    void deveCadastrarNovaProposta() throws Exception {

        PropostaRequest proposta = new PropostaRequest("09124768855", "gomes.mr@gmail.com", new BigDecimal(2500.0), "Rua Azul no. 1");

        RequestEntity<PropostaRequest> request = post("/propostas")
                .contentType(MediaType.APPLICATION_JSON)
                .body(proposta);

       // mvc.perform(proposta)
               // .andExpect(status().isCreated());

    }
}