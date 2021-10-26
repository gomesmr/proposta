package com.zup.proposta.carteira;

import com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private CarteiraTipo carteiraTipo = CarteiraTipo.PAYPAL;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {}

    public Carteira(String email, CarteiraTipo carteiraTipo, Cartao cartao) {
        this.email = email;
        this.carteiraTipo = carteiraTipo;
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", carteiraTipo=" + carteiraTipo +
                '}';
    }

    public CarteiraTipo getCarteiraTipo() {
        return carteiraTipo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carteira carteira = (Carteira) o;
        return carteiraTipo == carteira.carteiraTipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carteiraTipo);
    }
}

