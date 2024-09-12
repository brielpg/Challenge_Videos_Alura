package br.com.alura.desafioalura.dto.categorias;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCategoria(
        @NotNull(message = "O campo id é obrigatório")
        Long id,
        String titulo,
        String cor
) {
}
