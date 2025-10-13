package br.com.xareu.lift.DTO.Usuario;

import br.com.xareu.lift.DTO.Evento.EventoResponsePerfilDTO;
import br.com.xareu.lift.DTO.Meta.MetaResponsePerfilDTO;
import br.com.xareu.lift.DTO.Postagem.PostagemResponseImagemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponsePerfilDTO {

    private String nome;
    private String biografia;
    private String email;
    private String nomeUsuario;
    private List<MetaResponsePerfilDTO> metas;
    private List<EventoResponsePerfilDTO> eventos;
    private List<PostagemResponseImagemDTO> Postagens;


}
