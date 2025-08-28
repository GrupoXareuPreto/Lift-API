package br.com.xareu.lift.Service;

import br.com.xareu.lift.DTO.Resumos.*;
import br.com.xareu.lift.DTO.Normais.UsuarioDTO;
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
                usuario.getBiografia(),
                usuario.getSenha(),
                usuario.getMetas().stream().map(meta -> new MetaResumoDTO(
                        meta.getId(),
                        meta.getNome(),
                        meta.getDescricao(),
                        meta.getStatus()
                )).toList(),
                usuario.getPostagens().stream().map(postagem -> new PostagemResumoDTO(
                        postagem.getId(),
                        postagem.getTitulo(),
                        postagem.getMidia(),
                        postagem.getDescricao(),
                        postagem.getDataPublicacao()
                )).toList(),
                usuario.getCurtidas().stream().map(curtida -> new CurtidaResumoDTO(
                        curtida.getId()
                )).toList(),
                usuario.getComentarios().stream().map(comentario -> new ComentarioResumoDTO(
                        comentario.getId(),
                        comentario.getConteudo(),
                        comentario.getDataCriacao()
                )).toList(),
                usuario.getEventosCriados().stream().map(eventos -> new EventoResumoDTO(
                        eventos.getId(),
                        eventos.getDescricao(),
                        eventos.getTitulo(),
                        eventos.getLocalizacao(),
                        eventos.getAtividade(),
                        eventos.getDataInicio(),
                        eventos.getDataFim()
                )).toList(),
                usuario.getMensagens().stream().map(mensagem -> new MensagemResumoDTO(
                        mensagem.getId(),
                        mensagem.getDataEnvio(),
                        mensagem.getStatus()
                )).toList(),
                usuario.getCompartilhamentos().stream().map(compartilhamento -> new CompartilhamentoResumoDTO(
                        compartilhamento.getId()
                )).toList(),
                usuario.getEventosParticipar().stream().map(eventosParticipar -> new EventoResumoDTO(
                        eventosParticipar.getId(),
                        eventosParticipar.getDescricao(),
                        eventosParticipar.getTitulo(),
                        eventosParticipar.getLocalizacao(),
                        eventosParticipar.getAtividade(),
                        eventosParticipar.getDataInicio(),
                        eventosParticipar.getDataFim()
                )).toList(),
                usuario.getSeguidores().stream().map(seguidor -> new UsuarioResumoDTO(
                        seguidor.getId(),
                        seguidor.getNome(),
                        seguidor.getBiografia(),
                        seguidor.getEmail(),
                        seguidor.getSenha(),
                        seguidor.getNomeUsuario()
                )).toList(),
                usuario.getSeguindo().stream().map(seguindo -> new UsuarioResumoDTO(
                        seguindo.getId(),
                        seguindo.getNome(),
                        seguindo.getBiografia(),
                        seguindo.getEmail(),
                        seguindo.getSenha(),
                        seguindo.getNomeUsuario()
                )).toList(),
                usuario.getConversas().stream().map(conversa -> new ConversaResumoDTO(
                        conversa.getId(),
                        conversa.getFoto(),
                        conversa.getDescricao()
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
