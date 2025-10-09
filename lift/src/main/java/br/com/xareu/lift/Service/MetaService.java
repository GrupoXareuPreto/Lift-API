package br.com.xareu.lift.Service;

import br.com.xareu.lift.DTO.Meta.MetaRequestDTO;
import br.com.xareu.lift.DTO.Meta.MetaResponseDTO;
import br.com.xareu.lift.Entity.Meta;
import br.com.xareu.lift.Entity.Usuario;
import br.com.xareu.lift.Repository.MetaRepository;
import br.com.xareu.lift.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MetaService {

    private final MetaRepository metaRepository;

    public MetaService(MetaRepository metaRepository, UsuarioRepository usuarioRepository){
        this.metaRepository = metaRepository;
    }
/*--------------------------------------------------------------------------------------------------------------------*/
/* Parte de DTOs */

    private MetaResponseDTO toResponseDTO(Meta meta){
        if(meta == null){
            return null;
        }
        else {
            return new MetaResponseDTO(
                    meta.getNome(),
                    meta.isPublica(),
                    meta.getStatus(),
                    meta.getDataFim()
            );
        }
    }

/*--------------------------------------------------------------------------------------------------------------------*/

    @Transactional
    public MetaResponseDTO criarMeta(MetaRequestDTO metaDTO, Usuario autor) {
        Meta meta = new Meta();
        meta.setNome(metaDTO.getNome());
        meta.setPublica(metaDTO.isPublica());
        meta.setDataFim(metaDTO.getDataFim());
        meta.setAutor(autor);

        Meta savedMeta = metaRepository.save(meta);
        return toResponseDTO(savedMeta);
    }

    public List<MetaResponseDTO> getAll()
    {
        return metaRepository.findAll().stream().map(this ::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public Optional<MetaResponseDTO> atualizarMeta(Long metaId, MetaRequestDTO metaDTO, Usuario usuarioLogado) throws IllegalAccessException {
        Meta meta = metaRepository.findById(metaId)
                .orElseThrow(() -> new RuntimeException("Meta não encontrada"));

        // *** A VERIFICAÇÃO DE AUTORIZAÇÃO CRUCIAL ***
        if (!meta.getAutor().getId().equals(usuarioLogado.getId())) {
            throw new IllegalAccessException("Você não tem permissão para editar esta meta.");
        }

        // Atualiza os campos da meta existente
        meta.setNome(metaDTO.getNome());
        meta.setPublica(metaDTO.isPublica());
        meta.setStatus(metaDTO.getStatus());
        meta.setDataFim(metaDTO.getDataFim());
        // A data de início geralmente não é alterada, mas se for, adicione: meta.setDataInicio(metaDTO.getDataInicio());

        Meta metaAtualizada = metaRepository.save(meta);
        return Optional.of(toResponseDTO(metaAtualizada));
    }

    @Transactional
    public void deletarMeta(Long metaId, Usuario usuarioLogado) throws IllegalAccessException {
        Meta meta = metaRepository.findById(metaId)
                .orElseThrow(() -> new RuntimeException("Meta não encontrada"));

        // *** A VERIFICAÇÃO DE AUTORIZAÇÃO CRUCIAL ***
        if (!meta.getAutor().getId().equals(usuarioLogado.getId())) {
            throw new IllegalAccessException("Você não tem permissão para deletar esta meta.");
        }

        metaRepository.delete(meta);
    }

    public List<MetaResponseDTO> getMetasPorAutor(Usuario autor) {
        return metaRepository.findByAutor(autor).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MetaResponseDTO> getAllPublicas() {

        List<Meta> metasPublicas = metaRepository.findByPublicaTrue();


        return metasPublicas.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

}
