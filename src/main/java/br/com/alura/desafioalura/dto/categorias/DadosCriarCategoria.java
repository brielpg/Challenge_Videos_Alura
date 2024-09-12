package br.com.alura.desafioalura.dto.categorias;

import jakarta.validation.constraints.NotBlank;

public record DadosCriarCategoria(
        @NotBlank(message = "O campo título é obrigatório")
        String titulo,
        @NotBlank(message = "O campo cor é obrigatório")
        String cor
) {
}
