package br.com.alura.desafioalura.dto.videos;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarVideo(
        @NotNull(message = "O campo id é obrigatório")
        Long id,
        String titulo,
        String descricao,
        int categoriaId
) {
}
