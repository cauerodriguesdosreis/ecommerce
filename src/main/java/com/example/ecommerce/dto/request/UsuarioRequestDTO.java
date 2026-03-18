package com.example.ecommerce.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Este campo não deve estar vazio.")
    @Email (message = "E-mail inválido.")
    private String email;

    @NotBlank (message = "Este campo não deve estar vazio.")
    private String numero_telefone;

    @Size(min = 6, max = 18, message = "A senha deve ter entre 6 e 18 caracteres.")
    @NotBlank(message = "Este campo não deve estar vazio")
    private String senha;

}
