// VERSÃO SEGURA E CORRETA
package br.com.xareu.lift.Controller;

import br.com.xareu.lift.DTO.Postagem.PostagemRequestDTO;
import br.com.xareu.lift.DTO.Postagem.PostagemResponseFeedDTO;
import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Service.PostagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    // MELHORIA 1: Endpoint simplificado e seguro para criar postagem
    // O autor não vem mais da URL. Ele é obtido diretamente do token de autenticação.
    @PostMapping
    public ResponseEntity<PostagemResponseFeedDTO> createPostagem(
            @Valid @RequestBody PostagemRequestDTO postagemDTO,
            @AuthenticationPrincipal Usuario usuarioLogado) { // <-- MÁGICA ACONTECENDO AQUI

        PostagemResponseFeedDTO novaPostagem = postagemService.createPostagem(postagemDTO, usuarioLogado);
        return new ResponseEntity<>(novaPostagem, HttpStatus.CREATED);
    }

    // MELHORIA 2: Deletar postagem com verificação de permissão
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostagem(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuarioLogado) { // <-- Obtém o usuário que está tentando deletar

        try {
            postagemService.deletePostagem(id, usuarioLogado);
            return ResponseEntity.noContent().build();
        } catch (IllegalAccessException e) {
            // Lança um erro 403 Forbidden se o usuário não for o dono
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    // MELHORIA 3: Endpoint para ver apenas as MINHAS postagens
    @GetMapping("/me")
    public ResponseEntity<List<PostagemResponseFeedDTO>> getMinhasPostagens(@AuthenticationPrincipal Usuario usuarioLogado) {
        List<PostagemResponseFeedDTO> minhasPostagens = postagemService.getPostagensByAutor(usuarioLogado);
        return ResponseEntity.ok(minhasPostagens);
    }

    // Outros endpoints (GET por ID, GET todos, etc.) podem continuar existindo
    // e não precisam necessariamente do usuário logado, a menos que você tenha
    // postagens privadas.
}