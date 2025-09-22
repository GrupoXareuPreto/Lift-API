package br.com.xareu.lift.Service;

import br.com.xareu.lift.DTO.Mensagem.MensagemRequestDTO;
import br.com.xareu.lift.DTO.Mensagem.MensagemResponseDTO;
import br.com.xareu.lift.Entity.Mensagem;
import br.com.xareu.lift.Repository.ConversaRepository;
import br.com.xareu.lift.Repository.MensagemRepository;
import br.com.xareu.lift.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class MensagemService {
    private MensagemRepository repository;
    private UsuarioRepository usuarioRepository;
    private ConversaRepository conversaRepository;

    public MensagemService(MensagemRepository mensagemRepository, UsuarioRepository usuarioRepository, ConversaRepository conversaRepository){
        this.repository = mensagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.conversaRepository = conversaRepository;
    }


/*--------------------------------------------------------------------------------------------------------------------*/
/*Parte de DTOs*/

    private MensagemResponseDTO toResponseDTO(Mensagem mensagem){
        if(mensagem == null){
            return null;
        }
        else {
            return new MensagemResponseDTO(
                    mensagem.getConteudo(),
                    mensagem.getDataEnvio(),
                    mensagem.getStatus()
            );
        }
    }

/*--------------------------------------------------------------------------------------------------------------------*/


    public MensagemResponseDTO criarMensagem(MensagemRequestDTO mensagemDTO, Long autorId, Long conversaId){
        usuarioRepository.findById(autorId).orElseThrow(() -> new IllegalArgumentException("Autor não encontrado" + autorId));
        conversaRepository.findById(conversaId).orElseThrow(() -> new IllegalArgumentException("Conversa não encontrada" + conversaId));

        Mensagem mensagem = new Mensagem();
        mensagem.setConteudo(mensagemDTO.getConteudo());

        Mensagem savedMensagem = repository.save(mensagem);
        return toResponseDTO(savedMensagem);

    }
/*

    public List<MensagemResponseDTO> listarMensagensConversa(Long IdConversa) {
        Conversa conversa = conversaRepository.findById(IdConversa).orElseThrow(() -> new IllegalArgumentException("Conversa não encontrada" + IdConversa));

        List<Mensagem> mensagens = (List<Mensagem>) repository.findByConversa(IdConversa).get();

        return mensagens.stream().map(this :: toResponseDTO).collect(Collectors.toList());
    }
*/


    public boolean  deletarMensagem(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
