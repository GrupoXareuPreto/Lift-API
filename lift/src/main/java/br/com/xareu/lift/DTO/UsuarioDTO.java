package br.com.xareu.lift.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String nomeUsuario;
    private String email;
    private String senha;
    private List<PostagemResumoDTO> postagens;/*
    private List<MetaDTO> metas;
    private List<CurtidaDTO> curtidas;
    private List<ComentarioDTO> comentarios;
    private List<EventoDTO> eventosCriados;
    private List<MensagemDTO> mensagens;
    private List<CompartilhamentoDTO> compartilhamentos;
    private List<EventoDTO> eventosPartipar;
    private List<UsuarioDTO> seguindores;
    private List<UsuarioDTO> seguindo;
    private List<ConversaDTO> conversas;
    */

}
