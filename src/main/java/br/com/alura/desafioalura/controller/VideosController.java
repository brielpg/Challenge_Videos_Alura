package br.com.alura.desafioalura.controller;

import br.com.alura.desafioalura.dto.videos.DadosAtualizarVideo;
import br.com.alura.desafioalura.dto.videos.DadosCriarVideo;
import br.com.alura.desafioalura.dto.videos.DadosListagemVideo;
import br.com.alura.desafioalura.service.VideosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private VideosService videosService;

    @GetMapping
    public Page<DadosListagemVideo> listarVideos(Pageable paginacao){
        return videosService.listarVideos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarVideoPorId(@PathVariable Long id){
        return videosService.listarVideoPorId(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarVideo(@RequestBody @Valid DadosCriarVideo dados){
        return videosService.criarVideo(dados);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizarDadosVideo(@RequestBody @Valid DadosAtualizarVideo dados){
        return videosService.atualizarDadosVideo(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletarVideo(@PathVariable Long id){
        return videosService.deletarVideo(id);
    }

    @DeleteMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<String> ativarVideo(@PathVariable Long id){
        return videosService.ativarVideo(id);
    }
}
