package br.com.alura.desafioalura.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarVideo(
        @NotNull
        Long id,
        String titulo,
        String descricao
) {
}
