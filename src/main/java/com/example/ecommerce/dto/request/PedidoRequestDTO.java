package com.example.ecommerce.dto.request;

import com.example.ecommerce.enums.StatusDoPedido;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {

    private LocalDate momento;

    private StatusDoPedido status;

    @NotNull(message = "Este campo é obrigatório.")
    UUID clienteId;
}
