package com.example.ecommerce.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank (message = "Este campo não deve estar vazio.")
    private String nome;

    @Column (unique = true)
    private String email;

    private String numero_telefone;

    private String senha;

}
