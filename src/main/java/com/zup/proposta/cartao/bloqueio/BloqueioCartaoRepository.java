package com.zup.proposta.cartao.bloqueio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao, Long>{

    //@Query
}
