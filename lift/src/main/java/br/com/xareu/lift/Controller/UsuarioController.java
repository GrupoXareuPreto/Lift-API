package br.com.xareu.lift.Controller;

import br.com.xareu.lift.DTO.Usuario.UsuarioRequestAutenticarDTO;
import br.com.xareu.lift.DTO.Usuario.UsuarioRequestDTO;
import br.com.xareu.lift.DTO.Usuario.UsuarioResponseDTO;
import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class    UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service){
        this.service = service;
    }



    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> usuarios = service.getAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuario = service.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO) {
        try {
            UsuarioResponseDTO novoUsuario = service.criarUsuario(usuarioDTO);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> atualizarMeuPerfil(
            @Valid @RequestBody UsuarioRequestDTO usuarioDTO,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        UsuarioResponseDTO usuarioAtualizado = service.atualizarUsuarioLogado(usuarioDTO, usuarioLogado);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deletarMinhaConta(@AuthenticationPrincipal Usuario usuarioLogado) {
        service.deletarUsuarioLogado(usuarioLogado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> getMeuPerfil(@AuthenticationPrincipal Usuario usuarioLogado) {
        UsuarioResponseDTO meuPerfil = service.buscarPorId(usuarioLogado.getId());
        return ResponseEntity.ok(meuPerfil);
    }
}
