package br.com.xareu.lift.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TBL_CURTIDA")
@Data
public class Curtida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURTIDA")
    private Long id;

    /*private ... id_postagem*/
    @ManyToOne
    @JoinColumn(name = "postagem_id_postagem")
    private Postagem postagem;

    @ManyToOne
    @JoinColumn(name = "autor_id_usuario")
    private Usuario autor;



}
