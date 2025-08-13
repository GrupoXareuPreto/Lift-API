package br.com.xareu.lift.Entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.boot.spi.JpaOrmXmlPersistenceUnitDefaultAware;

import java.util.List;

@Entity
@Table(name = "TBL_USUARIO")
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TX_NOME")
    private String nome;

    @Column(name = "TX_EMAIL")
    private String email;

    /*Preciso ver como fazer o hash e o salt*/
    @Column(name = "")
    private String senha;

    @Column(name = "SEGUIDORES")
    private List<Usuario> seguidores;

    private List<Meta> metas; /*ta certo isso??*/

    private List<Conversa> conversas; /*ta certo isso??*/

    private List<Postagem> postagens; /*ta certo isso??*/

    /*Como guardar foto no banco*/
    /*private ... fotoDePerfil;*/

}
