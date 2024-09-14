package br.com.alura.desafioalura.controller;

import br.com.alura.desafioalura.dto.categorias.DadosAtualizarCategoria;
import br.com.alura.desafioalura.dto.categorias.DadosCriarCategoria;
import br.com.alura.desafioalura.dto.categorias.DadosListagemCategoria;
import br.com.alura.desafioalura.dto.videos.DadosListagemVideo;
import br.com.alura.desafioalura.service.CategoriasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @GetMapping
    public Page<DadosListagemCategoria> listarCategorias(Pageable paginacao){
        return categoriasService.listarCategorias(paginacao);
    }

    @GetMapping("/{id}/videos")
    public Page<DadosListagemVideo> listarCategoriaEVideo(@PathVariable Long id, Pageable paginacao){
        return categoriasService.listarCategoriaEVideo(id, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarCategoriaPorId(@PathVariable Long id){
        return categoriasService.listarCategoriaPorId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarCategoria(@RequestBody @Valid DadosCriarCategoria dados){
        return categoriasService.criarCategoria(dados);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizarCategoria(@RequestBody @Valid DadosAtualizarCategoria dados){
        return categoriasService.atualizarCategoria(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id){
        return categoriasService.deletarCategoria(id);
    }

    @DeleteMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<?> ativarCategoria(@PathVariable Long id){
        return categoriasService.ativarCategoria(id);
    }
}
