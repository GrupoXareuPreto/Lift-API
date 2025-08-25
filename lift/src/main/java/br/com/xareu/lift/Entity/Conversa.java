package br.com.xareu.lift.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TBL_CONVERSA")
@Data
public class Conversa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONVERSA")
    private Long id;

    @OneToMany(mappedBy = "conversa")
    @JsonManagedReference
    private List<Mensagem> mensagens;

    @ManyToMany(mappedBy = "conversas")
    @JsonManagedReference
    private List<Usuario> usuarios;


}
