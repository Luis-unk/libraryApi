package github.devluiss.libraryapi.controller;

import github.devluiss.libraryapi.controller.dto.CadastroLivroDTO;
import github.devluiss.libraryapi.controller.dto.ErroResposta;
import github.devluiss.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import github.devluiss.libraryapi.controller.mappers.LivroMapper;
import github.devluiss.libraryapi.exceptions.RegistroDuplicadoException;
import github.devluiss.libraryapi.model.Livro;
import github.devluiss.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController{

    private final LivroService livroService;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        try{
            //mapear dto para a entidade
            Livro livro = mapper.toEntity(dto);
            //enviar a entidade para o service validar e salvar na base
            livroService.salvar(livro);
            //criar url para acesso dos dados do livro
            var url = gerarHeaderLocation(livro.getId());
            //retornar codigo created com header location
            return ResponseEntity.created(url).build();
        }catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(
            @PathVariable("id") String id
    ){
        return livroService.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
