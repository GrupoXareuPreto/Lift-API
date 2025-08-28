package br.com.xareu.lift.Controller;

import br.com.xareu.lift.Entity.Postagem;
import br.com.xareu.lift.Service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagem")
public class PostagemController {

    @Autowired
    private PostagemService service;

    @GetMapping
    public List<Postagem> getAll(){
        return service.getAll();
    }

    @PostMapping
    public Postagem criarPostagem(@RequestBody Postagem postagemNova){
        return service.criarPostagem(postagemNova);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPostagem(@PathVariable Long id){
        Object deletado = service.deletarPostagem(id);

        if (deletado == ResponseEntity.noContent().build()){
            return ResponseEntity.ok().body("Postagem deletada com sucesso");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
