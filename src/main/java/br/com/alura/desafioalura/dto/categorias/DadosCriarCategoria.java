package br.com.alura.desafioalura.dto.categorias;

import jakarta.validation.constraints.NotBlank;

public record DadosCriarCategoria(
        @NotBlank
        String titulo,
        @NotBlank
        String cor
) {
}
