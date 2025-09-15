package br.com.xareu.lift.Service;

import br.com.xareu.lift.DTO.PostagemRequestDTO;
import br.com.xareu.lift.DTO.PostagemResponseDTO;
import br.com.xareu.lift.DTO.UsuarioResumoDTO;
import br.com.xareu.lift.Entity.Postagem;
import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Repository.PostagemRepository;
import br.com.xareu.lift.Repository.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostagemService {

    private PostagemRepository repository;
    private UsuarioRepository usuarioRepository;

    public PostagemService(PostagemRepository postagemRepository) {
        this.repository = postagemRepository;
    }
/*--------------------------------------------------------------------------------------------------------------------*/
/*Parte de DTOs*/


    private PostagemResponseDTO toResponseDTO(Postagem postagem){
        if(postagem == null){
            return null;
        }
        else {
            return new PostagemResponseDTO(
                    new UsuarioResumoDTO(postagem.getAutor()),
                    /*<--- Deve ser do tipo: UsuarioResumoDTO*/
                    postagem.getMidia(),
                    postagem.getTitulo(),
                    postagem.getDescricao(),
                    postagem.getDataPublicacao(),
                    postagem.getCurtidas() != null ? postagem.getCurtidas().size() : 0,
                    postagem.getComentarios() != null ? postagem.getComentarios().size() : 0,
                    postagem.getCompartilhamentos() != null ? postagem.getCompartilhamentos().size() : 0
            );
        }
    }


/*--------------------------------------------------------------------------------------------------------------------*/

    public List<PostagemResponseDTO> getFeed(){
      return repository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }


    public PostagemResponseDTO criarPostagem (PostagemRequestDTO postagemNova, Long autorId){
        Usuario autor = usuarioRepository.findById(autorId).orElseThrow(() -> new IllegalArgumentException("Autor nao encontrado" + autorId));

        Postagem postagem = new Postagem();
        postagem.setMidia(postagemNova.getMidia());
    }

    public boolean deletarPostagem(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}
