package com.example.ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoRequestDTO(
        BigDecimal valor,
        String metodo,
        String status,
        LocalDateTime dataPagamento,
        String numeroTransacao
)

{
    public PagamentoRequestDTO(PagamentoRequestDTO requestDTO) {
        this(requestDTO.valor, requestDTO.metodo, requestDTO.status, requestDTO.dataPagamento, requestDTO.numeroTransacao);
    }

}