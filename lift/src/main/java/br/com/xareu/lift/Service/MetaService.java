package br.com.xareu.lift.Service;

import br.com.xareu.lift.Entity.Meta;
import br.com.xareu.lift.Repository.MetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaService {

    private MetaRepository repository;

    public MetaService(MetaRepository metaRepository){
        this.repository = metaRepository;
    }

    public Meta criarMeta(Meta metaNova){
        return repository.save(metaNova);
    }

    public List<Meta> getAll(){
        return repository.findAll();
    }

    public Optional<Meta> atualizarMeta(Meta metaAtualizada, Long id){
        return repository.findById(id).map(metaBanco -> {
            metaBanco.setNome(metaAtualizada.getNome());
            metaBanco.setStatus(metaAtualizada.getStatus());
            metaBanco.setDescricao(metaAtualizada.getDescricao());

            return repository.save(metaBanco);
        });
    }

    public boolean deletarMeta(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}
