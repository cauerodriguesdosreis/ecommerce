package com.example.ecommerce.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErroResponseDTO {

    private int status;
    private String mensagem;
    private LocalDateTime timestamp;

}
