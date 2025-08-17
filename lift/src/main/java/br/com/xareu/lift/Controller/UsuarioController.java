package br.com.xareu.lift.Controller;

import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Repository.UsuarioRepository;
import br.com.xareu.lift.Service.UsuarioService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listar(){
        return service.ListarTodos();
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return service.criarUsuario(usuario);
    }

}
