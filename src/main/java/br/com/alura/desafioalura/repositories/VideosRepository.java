package br.com.alura.desafioalura.repositories;

import br.com.alura.desafioalura.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface VideosRepository extends JpaRepository<Video, Long> {
    Page<Video> findAllByAtivoTrue(Pageable paginacao);

    Page<Video> findAllByCategoriaId(Long id, Pageable paginacao);

    Page<Video> findByTituloContainingAndAtivoTrue(String search, Pageable paginacao);
}
