package com.zup.proposta.carteira;

import com.zup.proposta.cartao.Cartao;
import com.zup.proposta.config.validator.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraRequest {

    @UniqueValue(domainClass = Carteira.class, fieldName = "email", message = "O e-mail já existe no sistema")
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private CarteiraTipo carteiraTipo;

    Carteira toModel(Cartao cartao){
        return new Carteira(email, carteiraTipo, cartao);
    }

    public CarteiraRequest(String email, CarteiraTipo carteiraTipo) {
        this.email = email;
        this.carteiraTipo = carteiraTipo;
    }

    public String getEmail() {
        return email;
    }

    public CarteiraTipo getCarteiraTipo() {
        return carteiraTipo;
    }
}

