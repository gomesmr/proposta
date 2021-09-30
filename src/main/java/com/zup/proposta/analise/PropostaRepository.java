package com.zup.proposta.analise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository <Proposta, Long> {
    List<Proposta> findByDocumento(String documento);
    List<Proposta> findByStatusPropostaAndCartaoIsNull (StatusProposta statusProposta);
}
