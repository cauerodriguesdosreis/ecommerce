package com.example.ecommerce.dto.response;

import com.example.ecommerce.enums.StatusDoPedido;

import java.time.LocalDate;
import java.util.UUID;

public record PedidoResponseDTO(
        UUID id,
        LocalDate timestamp,
        StatusDoPedido status,
        UUID clienteId,
        UUID pagamentoId
) {
}