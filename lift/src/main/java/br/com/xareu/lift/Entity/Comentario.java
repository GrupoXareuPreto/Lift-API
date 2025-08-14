package br.com.xareu.lift.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TBL_COMENTARIO")
@Data

public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMENTARIO")
    private Long id;

    @Column(name = "TX_CONTEUDO")
    private String conteudo;

    /*@Column(name = "AUTOR")
    private Usuario autor;*/

    /*nao sei pq ta errado isso aqui, mas vou arrumar, são os mesmos erros que aparecem na classe Compartilhamento*/


}
