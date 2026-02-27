package com.example.ecommerce.dto.response;

import java.util.UUID;

public record PagamentoResponseDTO(
        UUID id,
        UUID pedidoId
) {
}