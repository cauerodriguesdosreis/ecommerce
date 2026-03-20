package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ItemDoPedido;
import com.example.ecommerce.entity.ItemDoPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemDoPedidoRepository extends JpaRepository <ItemDoPedido, ItemDoPedidoPK> {

}
