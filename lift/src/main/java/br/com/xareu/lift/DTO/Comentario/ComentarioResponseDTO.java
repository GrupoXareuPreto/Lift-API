package br.com.xareu.lift.DTO.Comentario;

import br.com.xareu.lift.DTO.Usuario.UsuarioResponseComentarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioResponseDTO {

    private UsuarioResponseComentarioDTO cardUsuario;
    private String conteudo;

}
