package github.devluiss.libraryapi.controller.mappers;

import github.devluiss.libraryapi.controller.dto.UsuarioDTO;
import github.devluiss.libraryapi.model.Usuario;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {


    Usuario toEntity(UsuarioDTO dto);
}
