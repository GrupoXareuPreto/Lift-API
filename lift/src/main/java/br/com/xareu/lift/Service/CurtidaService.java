package br.com.xareu.lift.Service;

import br.com.xareu.lift.Entity.Curtida;
import br.com.xareu.lift.Repository.CurtidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurtidaService {
    private CurtidaRepository curtidaRepository;

    public CurtidaService(CurtidaRepository curtidaRepository){
        this.curtidaRepository = curtidaRepository;
    }

    public Curtida criarcurtida(Curtida curtida){
        return curtidaRepository.save(curtida);
    }

    public List<Curtida> getAll(){
        return curtidaRepository.findAll();
    }

    /*Curtida não pode ser editada*/

    public boolean excluirCurtida(Long id){
        boolean existe = curtidaRepository.existsById(id);

        if(existe){
            curtidaRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}
