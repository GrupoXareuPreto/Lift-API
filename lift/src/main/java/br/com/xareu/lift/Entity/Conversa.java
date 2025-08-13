package br.com.xareu.lift.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TBL_CONVERSA")
@Getter
@Setter
public class Conversa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONVERSA")
    private Long id;

    @Column(name = "USUARIOS")
    private List<Usuario> usuarios/*usuarios que tem em uma conversa*/;

    @Column(name = "MENSAGENS")
    private List<Mensagem> mensagens;


}
