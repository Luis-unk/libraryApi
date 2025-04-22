package github.devluiss.libraryapi.controller.dto;

import github.devluiss.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "campo obrigatório") //igual not null porem para strings. ex strings vazias
        @Size(min = 2, max = 100, message = "campo fora do tamanho padrao")
        String nome,
        @NotNull(message = "campo obrigatório")
        @Past(message = "nao pode ser uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "campo obrigatório")
        @Size(max = 50, min = 2, message = "campo fora do tamanho padrao")
        String nacionalidade) {

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setId(this.id);
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }

}
