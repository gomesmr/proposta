package com.zup.proposta.cartao;

import com.zup.proposta.analise.Proposta;
import com.zup.proposta.cartao.avisoviagem.AvisoViagem;
import com.zup.proposta.cartao.biometria.BiometriaCartao;
import com.zup.proposta.cartao.bloqueio.AvisoBloqueioCartaoStatus;
import com.zup.proposta.cartao.bloqueio.BloqueioCartao;
import com.zup.proposta.carteira.Carteira;
import com.zup.proposta.carteira.CarteiraTipo;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotNull @OneToOne Proposta proposta;
    private @NotBlank String numero;
    private LocalDateTime dataEmissao;
    private @NotNull BigDecimal limite;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<BiometriaCartao> biometriaCartao = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<BloqueioCartao> listaBloqueios = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<AvisoViagem> listaAvisosViagem = new ArrayList<>();

    @JoinColumn(name = "cartao_id")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Carteira> carteiras = new ArrayList<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(Proposta proposta, String numero, LocalDateTime dataEmissao,
                  BigDecimal limite, Set<BiometriaCartao> biometria) {
        this.proposta = proposta;
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.limite = limite;
        this.biometriaCartao = biometria;
    }

    public Cartao(Optional<Cartao> cartao) {
        this.proposta = cartao.get().proposta;
        this.numero = cartao.get().numero;
        this.dataEmissao = cartao.get().getDataEmissao();
        this.limite = cartao.get().getLimite();
        this.biometriaCartao = cartao.get().getBiometriaCartao();
        System.out.println();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public String getNumero() {
        return numero;
    }
    public BigDecimal getLimite() {
        return limite;
    }

    public Set<BiometriaCartao> getBiometriaCartao() {
        return biometriaCartao;
    }

    public void adicionaBiometria(BiometriaCartao biometriaCartao) {
        this.biometriaCartao.add(biometriaCartao);
    }
    
    public BiometriaCartao retornaUltimaBiometria(){
        Iterator it = biometriaCartao.iterator();
        Long idMaior = 0L;
        BiometriaCartao biometeriaRetorno = null;
        while ((it.hasNext())){
            BiometriaCartao biometria = (BiometriaCartao) it.next();
            if (biometria.getId() > idMaior){
                idMaior = biometria.getId();
                biometeriaRetorno = biometria;
            }
        }
        return biometeriaRetorno;
    }

    public Proposta getProposta() {
        return proposta;
    }


    public boolean cartaoJaEstaBloqueado(){
        for(BloqueioCartao b: listaBloqueios){
            if(b.getStatusCartao() == AvisoBloqueioCartaoStatus.BLOQUEADO){
                return true;
            }
        }
        return false;
    }

    public void adicionaAviso(AvisoViagem avisoViagem) {
        this.listaAvisosViagem.add(avisoViagem);
    }

    public void adicionaCarteiraDigital(Carteira carteira) {
        carteiras.add(carteira);
    }

    public boolean possuiEstaCarteira(CarteiraTipo tipoCarteira) {
        return carteiras.stream().anyMatch(carteira -> carteira.getCarteiraTipo() == tipoCarteira);
    }

    public Carteira buscarCarteiraPorTipo(CarteiraTipo carteiraTipo) {
        return this.carteiras
                .stream()
                .filter(carteira1 -> carteira1.getCarteiraTipo() == carteiraTipo)
                .findFirst()
                .get();
    }

    public boolean verificaCartaoContemCarteira(CarteiraTipo carteiraTipo) {
        return carteiras.stream()
                        .anyMatch(carteira -> carteira.getCarteiraTipo() == carteiraTipo);
    }
}
