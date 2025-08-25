package br.com.xareu.lift.Service;

import br.com.xareu.lift.Entity.Comentario;
import br.com.xareu.lift.Repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    private ComentarioRepository comentarioRepository;

    public ComentarioService(ComentarioRepository comentarioRepository){
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> getAll(){
        return comentarioRepository.findAll();
    }
}
