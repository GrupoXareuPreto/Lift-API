package br.com.xareu.lift.Controller;

import br.com.xareu.lift.DTO.Meta.MetaRequestDTO;
import br.com.xareu.lift.DTO.Meta.MetaResponseDTO;
import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Service.MetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/meta")
public class MetaController {

    @Autowired
    private MetaService service;

    @PostMapping
    public ResponseEntity<MetaResponseDTO> criarMeta(
            @Valid @RequestBody MetaRequestDTO metaNova,
            @AuthenticationPrincipal Usuario usuarioLogado) { // Obtém o autor do token!

        MetaResponseDTO novaMeta = service.criarMeta(metaNova, usuarioLogado);
        return new ResponseEntity<>(novaMeta, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<List<MetaResponseDTO>> getMinhasMetas(@AuthenticationPrincipal Usuario usuarioLogado) {
        List<MetaResponseDTO> minhasMetas = service.getMetasPorAutor(usuarioLogado);
        return ResponseEntity.ok(minhasMetas);
    }

    @GetMapping("/publicas")
    public ResponseEntity<List<MetaResponseDTO>> getMetasPublicas() {
        List<MetaResponseDTO> metas = service.getAllPublicas();
        return ResponseEntity.ok(metas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetaResponseDTO> atualizarMeta(
            @PathVariable Long id,
            @Valid @RequestBody MetaRequestDTO metaDTO,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        try {
            return service.atualizarMeta(id, metaDTO, usuarioLogado)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalAccessException e) {
            // Se o serviço lançar a exceção de permissão, retorna 403 Forbidden.
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        } catch (RuntimeException e) { // Captura "Meta não encontrada"
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMeta(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        try {
            service.deletarMeta(id, usuarioLogado);
            return ResponseEntity.noContent().build();
        } catch (IllegalAccessException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
