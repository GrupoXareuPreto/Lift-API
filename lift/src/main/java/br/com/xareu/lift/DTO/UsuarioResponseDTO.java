package br.com.xareu.lift.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String biografia;
    private String email;
    private String nomeUsuario;

}
