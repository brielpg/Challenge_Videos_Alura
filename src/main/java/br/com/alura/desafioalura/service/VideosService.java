package br.com.alura.desafioalura.service;

import br.com.alura.desafioalura.dto.videos.DadosAtualizarVideo;
import br.com.alura.desafioalura.dto.videos.DadosCriarVideo;
import br.com.alura.desafioalura.dto.videos.DadosListagemVideo;
import br.com.alura.desafioalura.models.Video;
import br.com.alura.desafioalura.repositories.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VideosService {

    @Autowired
    private VideosRepository videosRepository;

    public Page<DadosListagemVideo> listarVideos(Pageable paginacao){
        return videosRepository.findAllByAtivoTrue(paginacao).map(DadosListagemVideo::new);
    }

    public Optional<?> listarVideoPorId(Long id) {
        if (videosRepository.existsById(id)){
            var video = videosRepository.getReferenceById(id);
            if (video.isAtivo()) {
                return Optional.of(new DadosListagemVideo(video));
            }
        }
        return Optional.of("Não encontrado.");
    }

    @Transactional
    public ResponseEntity<?> criarVideo(DadosCriarVideo dados) {
        var video = videosRepository.save(new Video(dados));
        video.setUrl("www.aluraflix.com/watch?id="+video.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(video);
    }

    @Transactional
    public ResponseEntity<?> atualizarDadosVideo(DadosAtualizarVideo dados) {
        if (videosRepository.existsById(dados.id())) {
            var video = videosRepository.getReferenceById(dados.id());
            if (video.isAtivo()) {
                video.atualizarDadosVideo(dados);
                return ResponseEntity.status(HttpStatus.OK).body(new DadosListagemVideo(video));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Video inativo.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não encontrado.");
    }

    @Transactional
    public ResponseEntity<String> deletarVideo(Long id) {
        var video = videosRepository.getReferenceById(id);
        if (video.isAtivo()){
            video.setAtivo(false);
            return ResponseEntity.status(HttpStatus.OK).body("Video desativado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Video ja esta desativado!");
    }

    public ResponseEntity<String> ativarVideo(Long id) {
        var video = videosRepository.getReferenceById(id);
        if (!video.isAtivo()){
            video.setAtivo(true);
            return ResponseEntity.status(HttpStatus.OK).body("Video ativado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Video ja esta ativo!");
    }
}
