package github.devluiss.libraryapi.controller.mappers;

import github.devluiss.libraryapi.controller.dto.AutorDTO;
import github.devluiss.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//transforma em um componente spring
@Mapper(componentModel = "spring")
public interface AutorMapper {

//    @Mapping(source = "nome", target = "nome") -> usado para mapear os atributos que possuem nomes diferentes
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
