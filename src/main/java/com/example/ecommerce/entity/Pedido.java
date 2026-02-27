package com.example.ecommerce.entity;

import com.example.ecommerce.enums.StatusDoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate timestamp;

    @Enumerated (EnumType.STRING)
    private StatusDoPedido status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @OneToOne (mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pagamento pagamento;
}
