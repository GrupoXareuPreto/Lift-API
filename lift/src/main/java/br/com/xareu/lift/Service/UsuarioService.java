package br.com.xareu.lift.Service;

import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    /*crud*/
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    public Usuario criarUsuario(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long id){
            return repository.findById(id).orElse(null);
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuarioatualizado, Long id){
        return repository.findById(id).map(usuario -> {
            usuario.setNome(usuarioatualizado.getNome());
            usuario.setEmail(usuarioatualizado.getEmail());
            usuario.setSenha(usuarioatualizado.getSenha());
            usuario.setNomeUsuario(usuarioatualizado.getNomeUsuario());
            usuario.setBiografia(usuarioatualizado.getBiografia());

            return repository.save(usuario);
        });
    }

    public boolean deletarUsuario(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
