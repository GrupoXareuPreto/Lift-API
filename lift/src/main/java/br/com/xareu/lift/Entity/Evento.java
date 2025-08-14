package br.com.xareu.lift.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TBL_EVENTO")
@Data

public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO")
    private Long id;

    @Column(name = "PRESENCA")
    /*duvida: como isso vai aparecer no banco??*/
    private List<Usuario> presenca;

    @Column(name = "TX_DESCRICAO")
    private String descricao;

    @Column(name = "TX_LOCALIZACAO")
    private String localizacao;

    @Column(name = "TP_ATIVIDADE")
    private String tipoAtividade;

    @Column(name = "DT_INICIO")
    private LocalDateTime dataInicio;

    /*private ... id_participantes*/

    @Column(name = "TX_TITULO")
    private String titulo;

    @Column(name = "DT_FIM")
    private LocalDateTime dataFim;

    @Column(name = "AUTOR")
    private String autor;

}
