package br.com.alura.desafioalura.dto.videos;

import br.com.alura.desafioalura.models.Video;
import jakarta.persistence.Column;

public record DadosListagemVideo(
        Long id,
        String titulo,
        String descricao,
        String url,
        int categoriaId
)
{

    public DadosListagemVideo(Video video){
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl(), video.getCategoriaId());
    }
}
