package br.com.xareu.lift.Controller;

import br.com.xareu.lift.Entity.Meta;
import br.com.xareu.lift.Service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meta")
public class MetaController {

    @Autowired
    private MetaService service;

    @GetMapping
    public List<Meta> getAll(){
        return service.getAll();
    }

    @PostMapping
    public Meta criarmeta(@RequestBody Meta meta){
        return service.criarMeta(meta);
    }

}
