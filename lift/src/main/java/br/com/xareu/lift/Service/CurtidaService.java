package br.com.xareu.lift.Service;

import br.com.xareu.lift.Repository.CurtidaRepository;
import org.springframework.stereotype.Service;

@Service
public class CurtidaService {
    private CurtidaRepository curtidaRepository;

    public CurtidaService(CurtidaRepository curtidaRepository){
        this.curtidaRepository = curtidaRepository;
    }
}
