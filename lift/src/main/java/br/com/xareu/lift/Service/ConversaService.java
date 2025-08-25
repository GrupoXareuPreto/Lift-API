package br.com.xareu.lift.Service;

import br.com.xareu.lift.Repository.ConversaRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversaService {
    private ConversaRepository conversaRepository;

    public ConversaService(ConversaRepository conversaRepository){
        this.conversaRepository = conversaRepository;
    }
}
