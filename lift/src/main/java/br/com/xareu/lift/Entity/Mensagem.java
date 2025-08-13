package br.com.xareu.lift.Entity;

import br.com.xareu.lift.Enum.StatusMensagemEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_MENSAGEM")
@Getter
@Setter

public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "ID_MENSAGEM")
    private Long  id;

    /*como colocar foreign key*/
    /*private ... id_conversa*/

    /*como colocar foreign key*/
    /*private ... id_autor*/

    @Column(name = "DT_ENVIO")
    private LocalDateTime data_envio;

    @Column(name = "STATUS_MENSAGEM")
    private StatusMensagemEnum status;

}
