package br.com.xareu.lift.Mapper;


import br.com.xareu.lift.DTO.Postagem.PostagemResponseFeedDTO;
import br.com.xareu.lift.DTO.Postagem.PostagemResponseImagemDTO;
import br.com.xareu.lift.Entity.Postagem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostagemMapper {
    PostagemMapper INSTANCE = Mappers.getMapper(PostagemMapper.class);

    PostagemResponseFeedDTO toResponseDTO(Postagem postagem);

    PostagemResponseImagemDTO toPostagemResponseImagemDTO(Postagem postagem);
}
