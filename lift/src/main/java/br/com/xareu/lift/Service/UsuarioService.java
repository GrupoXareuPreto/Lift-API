package br.com.xareu.lift.Service;

import br.com.xareu.lift.DTO.PostagemDTO;
import br.com.xareu.lift.DTO.PostagemResumoDTO;
import br.com.xareu.lift.DTO.UsuarioDTO;
import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /*crud*/
    public List<UsuarioDTO> getAll() {
        return usuarioRepository.findAll().stream().map(usuario -> new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getNomeUsuario(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getPostagens().stream().map(postagem -> new PostagemResumoDTO(
                        postagem.getId(),
                        postagem.getTitulo()
                )).toList()
        )).toList();
    }


    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id){
        try{
            return usuarioRepository.findById(id);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuarioatualizado, Long id){
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioatualizado.getNome());
            usuario.setEmail(usuarioatualizado.getEmail());
            usuario.setSenha(usuarioatualizado.getSenha());
            usuario.setNomeUsuario(usuarioatualizado.getNomeUsuario());
            usuario.setBiografia(usuarioatualizado.getBiografia());

            return usuarioRepository.save(usuario);
        });
    }

    public Object deletarUsuario(Long id){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound();
    }
}
