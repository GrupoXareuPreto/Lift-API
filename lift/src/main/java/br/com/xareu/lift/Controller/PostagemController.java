package br.com.xareu.lift.Controller;

import br.com.xareu.lift.DTO.Postagem.PostagemRequestDTO;
import br.com.xareu.lift.DTO.Postagem.PostagemResponseFeedDTO;
import br.com.xareu.lift.Service.PostagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagem")
public class PostagemController {

    @Autowired
    private PostagemService service;

    @PostMapping("usuario/{idAutor}")
    public ResponseEntity<PostagemResponseFeedDTO> criarPostagem(@Valid @RequestBody PostagemRequestDTO postagemNova, @PathVariable Long idAutor){
        try{
            PostagemResponseFeedDTO postagem = service.criarPostagem(postagemNova, idAutor);
            return new ResponseEntity<>(postagem, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/Feed")
    public List<PostagemResponseFeedDTO> getAll(){
        return service.getFeed();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPostagem(@PathVariable Long id){
        boolean deletado = service.deletarPostagem(id);

        if (deletado){
            return ResponseEntity.ok().body("Postagem deletada com sucesso");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
