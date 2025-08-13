package br.com.xareu.lift.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TBL_COMPARTILHAMENTO")
@Getter
@Setter
public class Compartilhamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMPARTILHAMENTO")
    private Long id;

    /*nao sei se isso ta ok*/
    /*private Postagem postagem;*/

    /*nao sei se isso ta ok tbm*/
    /*private Usuario autor;*/

}
