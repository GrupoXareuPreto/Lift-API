package br.com.xareu.lift.Controller;

import br.com.xareu.lift.Entity.Curtida;
import br.com.xareu.lift.Service.CurtidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curtida")
public class CurtidaController {

    @Autowired
    private CurtidaService curtidaService;


    @PostMapping
    public Curtida criarCurtida(){

    }


}
