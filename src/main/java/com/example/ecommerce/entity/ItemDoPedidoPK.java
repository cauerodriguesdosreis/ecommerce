package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class ItemDoPedidoPK implements Serializable {



    @ManyToOne
    @JoinColumn (name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn (name = "produto_id")
    private Produto produto;




}
