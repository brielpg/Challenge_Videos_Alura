package br.com.alura.desafioalura.models;

import br.com.alura.desafioalura.dto.categorias.DadosAtualizarCategoria;
import br.com.alura.desafioalura.dto.categorias.DadosCriarCategoria;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Categoria")
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = "id")
public class Categoria {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String titulo;
     private String cor;
     private boolean ativo;

    public Categoria(DadosCriarCategoria dados) {
        this.titulo = dados.titulo();
        this.cor = dados.cor();
        this.ativo = true;
    }

    public void atualizarCategoria(DadosAtualizarCategoria dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }

        if (dados.cor() != null){
            this.cor = dados.cor();
        }
    }
}
