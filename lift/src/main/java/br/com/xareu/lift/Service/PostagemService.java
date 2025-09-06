package br.com.xareu.lift.Service;

import br.com.xareu.lift.Entity.Postagem;
import br.com.xareu.lift.Repository.PostagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostagemService {

    private PostagemRepository repository;


    public PostagemService(PostagemRepository postagemRepository) {
        this.repository = postagemRepository;
    }

    /*crud*/
    public Postagem criarPostagem (Postagem postagemNova){
        return  repository.save(postagemNova);
    }

    public List<Postagem> getAll(){
        return repository.findAll();
    }

    /*Postagem nao pode ser editada!!!!*/

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
