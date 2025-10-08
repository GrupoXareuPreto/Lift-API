package br.com.xareu.lift.Service;

import br.com.xareu.lift.DTO.Postagem.PostagemRequestDTO;
import br.com.xareu.lift.DTO.Postagem.PostagemResponseFeedDTO;
import br.com.xareu.lift.Entity.Postagem;
import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Repository.PostagemRepository;
import br.com.xareu.lift.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostagemService {

    private PostagemRepository repository;
    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;

    public PostagemService(PostagemRepository postagemRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService)
    {
        this.repository = postagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }
/*--------------------------------------------------------------------------------------------------------------------*/
/*Parte de DTOs*/


    private PostagemResponseFeedDTO toResponseDTO(Postagem postagem){
        if(postagem == null){
            return null;
        }
        else {
            return new PostagemResponseFeedDTO(
                    usuarioService.toUsuarioCardPostagemEventoDTO(postagem.getAutor()),
                    postagem.getMidia(),
                    postagem.getTitulo(),
                    postagem.getDataPublicacao(),
                    postagem.getCurtidas() != null ? postagem.getCurtidas().size() : 0,
                    postagem.getComentarios() != null ? postagem.getComentarios().size() : 0,
                    postagem.getCompartilhamentos()
            );
        }
    }


/*--------------------------------------------------------------------------------------------------------------------*/

    @Autowired
    private PostagemRepository postagemRepository;

    // MELHORIA 1: O metodo agora recebe o objeto Usuario, não mais um ID.
    // Não há necessidade de buscar o usuário no banco, ele já veio autenticado.
    @Transactional
    public PostagemResponseFeedDTO createPostagem(PostagemRequestDTO dto, Usuario autor) {
        Postagem postagem = new Postagem();
        // ... seta os dados da postagem a partir do DTO (título, conteúdo, etc.) ...
        postagem.setAutor(autor); // Define o autor como o usuário que está logado

        Postagem savedPostagem = postagemRepository.save(postagem);
        return toResponseDTO(savedPostagem); // Supondo que você tenha um método de conversão
    }

    // MELHORIA 2: O metodo agora verifica se o usuário logado é o dono da postagem.
    @Transactional
    public void deletePostagem(Long postagemId, Usuario usuarioLogado) throws IllegalAccessException {
        // Busca a postagem no banco
        Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada"));

        // *** A VERIFICAÇÃO DE AUTORIZAÇÃO CRUCIAL ***
        if (!postagem.getAutor().getId().equals(usuarioLogado.getId())) {
            // Se o ID do autor da postagem for diferente do ID do usuário logado, lança uma exceção.
            throw new IllegalAccessException("Você não tem permissão para deletar esta postagem.");
        }

        postagemRepository.delete(postagem);
    }

    // MELHORIA 3: Busca postagens pelo objeto autor
    public List<PostagemResponseFeedDTO> getPostagensByAutor(Usuario autor) {
        return postagemRepository.findByAutor(autor).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
