package br.com.alura.desafioalura.service;

import br.com.alura.desafioalura.dto.categorias.DadosAtualizarCategoria;
import br.com.alura.desafioalura.dto.categorias.DadosCriarCategoria;
import br.com.alura.desafioalura.dto.categorias.DadosListagemCategoria;
import br.com.alura.desafioalura.dto.videos.DadosListagemVideo;
import br.com.alura.desafioalura.models.Categoria;
import br.com.alura.desafioalura.repositories.CategoriasRepository;
import br.com.alura.desafioalura.repositories.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private VideosRepository videosRepository;

    public Page<DadosListagemCategoria> listarCategorias(Pageable paginacao) {
        return categoriasRepository.findByAtivoTrue(paginacao).map(DadosListagemCategoria::new);
    }

    public ResponseEntity<?> listarCategoriaPorId(Long id) {
        if (categoriasRepository.existsById(id)) {
            var categoria = categoriasRepository.getReferenceById(id);
            if (categoria.isAtivo()) {
                return ResponseEntity.ok(new DadosListagemCategoria(categoria));
            }
            return ResponseEntity.badRequest().body("Erro: Categoria desativada.");
        }
        return ResponseEntity.badRequest().body("Erro: Não encontrado.");
    }

    @Transactional
    public ResponseEntity<?> deletarCategoria(Long id) {
        if (categoriasRepository.existsById(id)) {
            var categoria = categoriasRepository.getReferenceById(id);
            if (categoria.isAtivo()) {
                categoria.setAtivo(false);
                return ResponseEntity.ok().body("Categoria desativada com sucesso!");
            }
            return ResponseEntity.badRequest().body("Erro: Categoria ja esta desativada!");
        }
        return ResponseEntity.badRequest().body("Erro: Não encontrado.");
    }

    @Transactional
    public ResponseEntity<?> ativarCategoria(Long id) {
        var categoria = categoriasRepository.getReferenceById(id);
        if (!categoria.isAtivo()) {
            categoria.setAtivo(true);
            return ResponseEntity.ok().body("Categoria ativada com sucesso!");
        }
        return ResponseEntity.badRequest().body("Erro: Categoria ja esta ativada!");
    }


    @Transactional
    public ResponseEntity<?> criarCategoria(DadosCriarCategoria dados) {
        if (!categoriasRepository.existsByTitulo(dados.titulo())){
            var categoria = categoriasRepository.save(new Categoria(dados));
            return ResponseEntity.status(HttpStatus.CREATED).body(new DadosListagemCategoria(categoria));
        }
        return ResponseEntity.badRequest().body("Erro: categoria ja existe");
    }

    @Transactional
    public ResponseEntity<?> atualizarCategoria(DadosAtualizarCategoria dados) {
        if (categoriasRepository.existsById(dados.id())) {
            var categoria = categoriasRepository.getReferenceById(dados.id());
            if (categoria.isAtivo()) {
                categoria.atualizarCategoria(dados);
                return ResponseEntity.ok(new DadosListagemCategoria(categoria));
            }
            return  ResponseEntity.badRequest().body("Erro: Categoria desativada.");
        }
        return  ResponseEntity.badRequest().body("Erro: Não encontrado.");
    }

    public Page<DadosListagemVideo> listarCategoriaEVideo(Long id, Pageable paginacao) {
        return videosRepository.findAllByCategoriaId(id, paginacao).map(DadosListagemVideo::new);
    }
}
