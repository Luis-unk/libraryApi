package github.devluiss.libraryapi.controller.mappers;

import github.devluiss.libraryapi.controller.dto.CadastroLivroDTO;
import github.devluiss.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import github.devluiss.libraryapi.model.Livro;
import github.devluiss.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

//uses -> Diz para o mapper que caso ele precise, pode utilizar o AutorMapper.class
//ex: @AutoWired na classe targer do mapper;
@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);
}