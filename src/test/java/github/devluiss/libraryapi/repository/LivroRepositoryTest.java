package github.devluiss.libraryapi.repository;

import github.devluiss.libraryapi.model.Autor;
import github.devluiss.libraryapi.model.GeneroLivro;
import github.devluiss.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;


@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    //sem utilizar cascade
    @Test
    void salvarTest(){
        Livro livro = new Livro();

        livro.setIsbn("98999-898");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(2024, 3, 12));

        Autor autor = autorRepository.findById(3).orElse(null);

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void salvarCascadeTest(){

        //criando minha entidade livro
        Livro livro = new Livro();
        livro.setIsbn("98999-898");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(2024, 3, 12));

        //criadno minha entidade autor
        Autor autor = new Autor();
        autor.setNome("Rafel");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2002, 1, 20));

        livro.setAutor(autor);
        repository.save(livro); //salvando livro e autor em cascata(cascade)
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        int id = 5;
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());

    }
}