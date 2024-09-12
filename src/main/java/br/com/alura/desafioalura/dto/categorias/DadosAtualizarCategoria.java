package br.com.alura.desafioalura.dto.categorias;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarCategoria(
        @NotNull
        Long id,
        String titulo,
        String cor
) {
}
