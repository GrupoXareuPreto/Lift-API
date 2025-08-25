package br.com.xareu.lift.Service;

import br.com.xareu.lift.Entity.Postagem;
import br.com.xareu.lift.Repository.PostagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    private PostagemRepository postagemRepository;

    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    public List<Postagem> getAll(){
        return postagemRepository.findAll();
    }

    public Postagem criarPostagem (Postagem postagemNova){
        return  postagemRepository.save(postagemNova);
    }

    public boolean deletarPostagem(Long id){

    }
}
