package github.devluiss.libraryapi.controller;

import github.devluiss.libraryapi.controller.dto.CadastroLivroDTO;
import github.devluiss.libraryapi.controller.dto.ErroResposta;
import github.devluiss.libraryapi.exceptions.RegistroDuplicadoException;
import github.devluiss.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        try{
            //mapear dto para a entidade
            //enviar a entidade para o service validar e salvar na base
            //criar url para acesso dos dados do livro
            //retornar codigo created com header location
            return ResponseEntity.ok(dto);
        }catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

}
