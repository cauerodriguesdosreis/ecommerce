package com.example.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String email;

    private String numero_telefone;

    private String senha;

    private String roles;

    @OneToMany (mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Usuario(String nome, String email, String numero_telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.numero_telefone = numero_telefone;
        this.senha = senha;
    }
}
