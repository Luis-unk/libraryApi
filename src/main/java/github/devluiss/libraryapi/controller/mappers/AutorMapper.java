package github.devluiss.libraryapi.controller.mappers;

import github.devluiss.libraryapi.controller.dto.AutorDTO;
import github.devluiss.libraryapi.model.Autor;
import org.mapstruct.Mapper;

//transforma em um componente spring
@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
