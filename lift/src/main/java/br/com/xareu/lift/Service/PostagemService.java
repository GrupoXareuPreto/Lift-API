package br.com.xareu.lift.Service;

import br.com.xareu.lift.DTO.Resumos.CurtidaResumoDTO;
import br.com.xareu.lift.DTO.Normais.PostagemDTO;
import br.com.xareu.lift.Entity.Postagem;
import br.com.xareu.lift.Repository.PostagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostagemService {

    private PostagemRepository postagemRepository;

    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    /*crud*/
    public Postagem criarPostagem (Postagem postagemNova){
        return  postagemRepository.save(postagemNova);
    }

    public List<PostagemDTO> getAll(){
        return postagemRepository.findAll().stream().map(postagem -> new PostagemDTO(
                postagem.getId(),
                postagem.getMidia(),
                postagem.getTitulo(),
                postagem.getDescricao(),
                postagem.getDataPublicacao(),
                postagem.getCurtidas().stream().map(curtida -> new CurtidaResumoDTO(
                        curtida.getId()
                )).toList(),
                postagem.get

        )).toList();
    }

    /*Postagem nao pode ser editada!!!!*/

    public Object deletarPostagem(Long id){
        if(postagemRepository.existsById(id)){
            postagemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound();
        }
    }
}
