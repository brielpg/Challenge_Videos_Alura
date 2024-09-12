package br.com.alura.desafioalura.repositories;

import br.com.alura.desafioalura.models.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categoria, Long> {
    Page<Categoria> findByAtivoTrue(Pageable paginacao);

    boolean existsByTitulo(String titulo);
}
