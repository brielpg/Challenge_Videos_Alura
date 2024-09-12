package br.com.alura.desafioalura.dto.categorias;

import br.com.alura.desafioalura.models.Categoria;

public record DadosListagemCategoria(
        Long id,
        String titulo,
        String cor
) {

    public DadosListagemCategoria(Categoria categoria){
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
    }
}
