package br.com.xareu.lift.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PostagemDTO {

    private Long id;
    private String midia;
    private String titulo;
    private String descricao;
    private LocalDateTime dataPublicacao;
    private List<CurtidaDTO> curtidas;
    private List<ComentarioDTO> comentarios;
    private List<CompartilhamentoDTO> compartilhamentos;
}
