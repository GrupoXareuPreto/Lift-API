package br.com.xareu.lift.DTO.Comentario;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioRequestDTO {

    @NotBlank(message = "O comnetario deve obrigatoriamente ter conteudo")
    @NotNull
    @Max(200)
    private String conteudo;

}
