package br.com.xareu.lift.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaRequestDTO {

    @NotBlank(message = "A meta deve conter um nome")
    private String nome;

    @NotNull(message = "O  status de visibilidade da meta é obrigatório")
    private boolean publica;
}
