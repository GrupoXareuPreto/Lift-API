package br.com.xareu.lift.Service;

import br.com.xareu.lift.Entity.Comentario;
import br.com.xareu.lift.Entity.Mensagem;
import br.com.xareu.lift.Repository.MensagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MensagemService {
    private MensagemRepository mensagemRepository;

    public MensagemService(MensagemRepository mensagemRepository){this.mensagemRepository = mensagemRepository;}

    public Object  deletarMensagem(Long id){
        if (mensagemRepository.existsById(id)){
            mensagemRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound();
    }
}
