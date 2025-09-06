package br.com.xareu.lift.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaResponseDTO {

    private Long id;
    private String nome;
    private boolean publica;
    private Long autorId;
    private String autorNomeUsuario;
}
