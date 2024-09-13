package br.com.alura.desafioalura.dto.videos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record DadosCriarVideo(
        @NotBlank(message = "O campo título é obrigatório")
        String titulo,
        @NotBlank(message = "O campo descricao é obrigatório")
        String descricao,
        int categoriaId
) {
}
