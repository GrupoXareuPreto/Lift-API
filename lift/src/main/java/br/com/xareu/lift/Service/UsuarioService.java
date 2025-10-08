package br.com.xareu.lift.Service;

import br.com.xareu.lift.DTO.Usuario.*;
import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.repository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /*--------------------------------------------------------------------------------------------------------------------*/
    /*Parte de DTOs*/
    private UsuarioResponseDTO toResponseDTO(Usuario usuario){
        if(usuario == null){
            return null;
        }
        return new UsuarioResponseDTO(
                usuario.getNome(),
                usuario.getBiografia(),
                usuario.getEmail(),
                usuario.getNomeUsuario()
        );
    }

    public UsuarioResponseCardPostagemEventoDTO toUsuarioCardPostagemEventoDTO(Usuario usuario){
        if(usuario == null){
            return null;
        }
        else {
            return new UsuarioResponseCardPostagemEventoDTO(
                    usuario.getFotoPerfil(),
                    usuario.getNome(),
                    usuario.getNomeUsuario()
            );
        }
    }

    public UsuarioResponseCardConversaDTO toUsuarioCardConversaDTO(Usuario usuario){
        if(usuario == null){
            return null;
        }
        else {
            return new UsuarioResponseCardConversaDTO(
                    usuario.getId(),
                    usuario.getFotoPerfil(),
                    usuario.getNome()
            );
        }
    }

    public UsuarioResponseComentarioDTO toUsuarioResponseComentarioDTO(Usuario usuario){
        if(usuario == null){
            return null;
        }
        else {
            return new UsuarioResponseComentarioDTO(
                    usuario.getFotoPerfil(),
                    usuario.getNomeUsuario()
            );

        }
    }

    private Usuario toEntity(UsuarioRequestDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNomee());
        usuario.setBiografia(dto.getBiografia());
        usuario.setEmail(dto.getEmail());
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        return usuario;
    }
/*--------------------------------------------------------------------------------------------------------------------*/

    /*crud*/
    public List<UsuarioResponseDTO> getAll() {
        return repository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioDTO) {
        if (repository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já existe");
        }
        if (repository.findByNomeUsuario(usuarioDTO.getNomeUsuario()).isPresent()) {
            throw new IllegalArgumentException("Nome de Usuario já existe");
        }

        Usuario usuario = toEntity(usuarioDTO);
        Usuario usuarioSalvo = repository.save(usuario);
        return toResponseDTO(usuarioSalvo);
    }


    public UsuarioResponseDTO buscarPorId(Long id){
         return repository.findById(id).map(this :: toResponseDTO).orElse(null);
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuarioLogado(UsuarioRequestDTO usuarioAtualizadoDTO, Usuario usuarioLogado) {
        // O usuarioLogado já é a entidade que queremos atualizar.
        usuarioLogado.setNome(usuarioAtualizadoDTO.getNomee());
        usuarioLogado.setNomeUsuario(usuarioAtualizadoDTO.getNomeUsuario());
        usuarioLogado.setBiografia(usuarioAtualizadoDTO.getBiografia());
        usuarioLogado.setEmail(usuarioAtualizadoDTO.getEmail());

        // A senha só deve ser atualizada se for explicitamente fornecida.
        if (usuarioAtualizadoDTO.getSenha() != null && !usuarioAtualizadoDTO.getSenha().isEmpty()) {
            usuarioLogado.setSenha(passwordEncoder.encode(usuarioAtualizadoDTO.getSenha()));
        }

        Usuario usuarioAtualizado = repository.save(usuarioLogado);
        return toResponseDTO(usuarioAtualizado); // Supondo que você tenha um `toResponseDTO`
    }

    @Transactional
    public void deletarUsuarioLogado(Usuario usuarioLogado) {
        // Simplesmente deleta o usuário que foi passado (que veio do token).
        repository.deleteById(usuarioLogado.getId());
    }

    public Optional<UsuarioResponseDTO> autenticarUsuario(UsuarioRequestAutenticarDTO credenciais){
        try{
            Optional<Usuario> usuarioOptional = repository.findByNomeUsuario(credenciais.getNomeUsuarioEmail());
            if (usuarioOptional.isEmpty()){
                return Optional.empty();
            }
            Usuario usuario = usuarioOptional.get();
            if(passwordEncoder.matches(credenciais.getSenha(), usuario.getSenha())){
                return Optional.of(toResponseDTO(usuario));
            }
            return Optional.empty();

        }catch (Exception ex){
            System.out.println("ERRO"+ex.getMessage());
            return Optional.empty();
        }

    }
}
