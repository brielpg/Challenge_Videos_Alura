package br.com.alura.desafioalura.service;

import br.com.alura.desafioalura.dto.videos.DadosAtualizarVideo;
import br.com.alura.desafioalura.dto.videos.DadosCriarVideo;
import br.com.alura.desafioalura.dto.videos.DadosListagemVideo;
import br.com.alura.desafioalura.models.Video;
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
public class VideosService {

    @Autowired
    private VideosRepository videosRepository;

    public Page<DadosListagemVideo> listarVideos(Pageable paginacao, String search){
        if (search == null || search.isEmpty()) {
            return videosRepository.findAllByAtivoTrue(paginacao).map(DadosListagemVideo::new);
        }
        return videosRepository.findByTituloContainingAndAtivoTrue(search, paginacao).map(DadosListagemVideo::new);
    }

    public ResponseEntity<?> listarVideoPorId(Long id) {
        if (videosRepository.existsById(id)){
            var video = videosRepository.getReferenceById(id);
            if (video.isAtivo()) {
                return ResponseEntity.ok().body(new DadosListagemVideo(video));
            }
            return ResponseEntity.badRequest().body("Erro: Video inativo.");
        }
        return ResponseEntity.badRequest().body("Erro: Não encontrado.");
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
                return ResponseEntity.ok().body(new DadosListagemVideo(video));
            }
            return ResponseEntity.badRequest().body("Erro: Video inativo.");
        }
        return ResponseEntity.badRequest().body("Erro: Não encontrado.");
    }

    @Transactional
    public ResponseEntity<String> deletarVideo(Long id) {
        var video = videosRepository.getReferenceById(id);
        if (video.isAtivo()){
            video.setAtivo(false);
            return ResponseEntity.ok().body("Video desativado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Erro: Video ja esta desativado!");
    }

    public ResponseEntity<String> ativarVideo(Long id) {
        var video = videosRepository.getReferenceById(id);
        if (!video.isAtivo()){
            video.setAtivo(true);
            return ResponseEntity.ok().body("Video ativado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Erro: Video ja esta ativo!");
    }
}
