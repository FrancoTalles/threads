package com.tweetero.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {

    @NotBlank
    @Size(max = 300, message = "Tamanho máximo do link do avatar é 300 caracteres")
    private String avatar;

    @NotBlank
    @Size(max = 150, message = "Tamanho máximo de nome de usuário é 150 caracteres")
    private String username;
}
