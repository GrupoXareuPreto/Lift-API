package br.com.xareu.lift.Service;

import br.com.xareu.lift.DTO.Evento.EventoRequestCriarDTO;
import br.com.xareu.lift.DTO.Evento.EventoResponseFeedDTO;
import br.com.xareu.lift.Entity.Evento;
import br.com.xareu.lift.Repository.EventoRepository;
import br.com.xareu.lift.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {
    private EventoRepository repository;
    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;


    public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository){
        this.repository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }
/*--------------------------------------------------------------------------------------------------------------------*/
/*Parte de DTOs*/

    private EventoResponseFeedDTO toResponseFeedDTO(Evento evento){
        if(evento == null){
            return null;
        }
        else {
            return new EventoResponseFeedDTO(
                    usuarioService.toUsuarioCardPostagemEventoDTO(evento.getAutor()),
                    evento.getMidia(),
                    evento.getDataInicio(),
                    evento.get
            );
        }

    }










/*--------------------------------------------------------------------------------------------------------------------*/





    public EventoResponseFeedDTO criarEvento(EventoRequestCriarDTO dto, Long autorId)
    {
        usuarioRepository.findById(autorId).orElseThrow(() -> new IllegalArgumentException("Um evento não pode ser criado sem um autor válido"));

        Evento evento = new Evento();
        evento.setTitulo(dto.getTitulo());
        if(!dto.getDescricao().trim().isEmpty()){
            evento.setDescricao(dto.getDescricao());
        }
        evento.setLocalizacao(dto.getLocalizacao());
        evento.setDataInicio(dto.getDataInicio());
        evento.setDataFim(dto.getDataFim());




    }

    public List<Evento> getAll(){
        return repository.findAll();
    }



    public boolean  deletarEvento(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Evento> atualizarEvento(Evento atualizacao, Long id){
        return repository.findById(id).map(eventoAtualizado -> {
            eventoAtualizado.setDescricao(atualizacao.getDescricao());
            eventoAtualizado.setLocalizacao(atualizacao.getLocalizacao());
            eventoAtualizado.setDataInicio(atualizacao.getDataInicio());
            eventoAtualizado.setDataFim(atualizacao.getDataFim());
            eventoAtualizado.setTitulo(atualizacao.getTitulo());

            return repository.save(eventoAtualizado);
        });
    }
}
