package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pedido_id")
    // vincula o id do pedido ao id do pagamento, facilitando o controle no banco de dados
    private Pedido pedido;
}