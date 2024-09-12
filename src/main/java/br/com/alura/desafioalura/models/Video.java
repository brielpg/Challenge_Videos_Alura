package br.com.alura.desafioalura.models;

import br.com.alura.desafioalura.dto.videos.DadosAtualizarVideo;
import br.com.alura.desafioalura.dto.videos.DadosCriarVideo;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Video")
@Table(name = "videos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private boolean ativo;

    public Video(DadosCriarVideo dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.url = "www.aluraflix.com";
        this.ativo = true;
    }

    public void atualizarDadosVideo(DadosAtualizarVideo dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }

        if (dados.descricao() != null){
            this.descricao = dados.descricao();
        }
    }
}
