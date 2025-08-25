package br.com.xareu.lift.Entity;

import br.com.xareu.lift.Enum.StatusMensagemEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_MENSAGEM")
@Data

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
    @Enumerated(EnumType.STRING)
    private StatusMensagemEnum status;

    @ManyToOne
    @JoinColumn(name = "ID_CONVERSA", nullable = false)
    @JsonBackReference
    private Conversa conversa;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @JsonBackReference
    private Usuario autor;

    @OneToOne(mappedBy = "mensagem", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Compartilhamento compartilhamento;

}
