package br.com.xareu.lift.Entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "TBL_POSTAGEM")
@Getter
@Setter
public class Postagem {

    @Id
    @Column(name = "ID_POSTAGEM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*como guardar as midias no banco*/
    /*private ... midia;*/

    @Column(name = "TX_DESCRICAO")
    private String descricao;

    @Column(name = "DT_PUBLICACAO")
    private LocalDateTime dataPublicacao;

    /*como coloca foreign key*/
    /*private ... id_autor */

    /*como coloca foreign key*/
    /*private ... id_comentario */


}
