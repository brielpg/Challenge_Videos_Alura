package br.com.alura.desafioalura.models;

import br.com.alura.desafioalura.dto.DadosAtualizarVideo;
import br.com.alura.desafioalura.dto.DadosCriarVideo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Video")
@Table(name = "videos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    public void desativar() {
        this.ativo = false;
    }

    public boolean estaAtivo() {
        return this.ativo;
    }

    public void ativar() {
        this.ativo = true;
    }
}
