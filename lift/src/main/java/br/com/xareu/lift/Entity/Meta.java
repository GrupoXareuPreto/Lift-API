package br.com.xareu.lift.Entity;

import br.com.xareu.lift.Enum.StatusMetaEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Table(name = "TBL_METAS")
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_META")
    private Long id;

    /*como coloca foreign key*/
    /*private ... id_usuario*/

    @OneToOne
    @JoinColumn(name = "usuario_id_usuario")
    private Usuario usuario/*Dono das metas*/;

    @Column(name = "TX_NOME")
    private String nome;

    @Column(name = "TX_DESCRICAO")
    private String descricao;

    @Column(name = "STATUS_META")
    private StatusMetaEnum status;

}
