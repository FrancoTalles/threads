package com.tweetero.api.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class TweetDTO {
    @NotBlank
    @Size(max = 300, message = "Tamanho máximo do tweet é de 300 caracteres")
    private String text;

    @NotNull
    private UUID userId;
}
