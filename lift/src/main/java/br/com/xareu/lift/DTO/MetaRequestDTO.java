package br.com.xareu.lift.DTO;

import br.com.xareu.lift.Enum.StatusMetaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaRequestDTO {

    @NotBlank(message = "A meta deve conter um nome")
    private String nome;

    @NotNull(message = "O  status de visibilidade da meta é obrigatório")
    private boolean publica;


    private StatusMetaEnum status = StatusMetaEnum.PENDENTE;

    private LocalDateTime dataInicio = LocalDateTime.now();

    private LocalDate dataFim;
}
