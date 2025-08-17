package br.com.xareu.lift.Service;

import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public List<Usuario> ListarTodos() {return usuarioRepository.findAll();}


    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


}
