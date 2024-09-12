package br.com.alura.desafioalura.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCriarVideo(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao
) {
}
