package com.example.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    BigDecimal valor;
    String metodo;
    String status;
    LocalDateTime dataPagamento;
    String numeroTransacao;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pedido_id")
    // vincula o id do pedido ao id do pagamento, facilitando o controle no banco de dados
    private Pedido pedido;
}