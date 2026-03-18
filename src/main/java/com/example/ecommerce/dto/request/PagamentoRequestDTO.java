package com.example.ecommerce.dto.request;


import com.example.ecommerce.entity.Pagamento;
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
public class PagamentoRequestDTO {

    private UUID id;
    LocalDate dataPagamento;


    public PagamentoRequestDTO(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.dataPagamento = pagamento.getDataPagamento();
    }

}