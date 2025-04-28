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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        Autor autor = autorRepository.findById(UUID.fromString("f77e5406-81e4-40c7-9835-56edae600525")).orElse(null);

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
        int id = 1;
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest(){
        Optional<Livro> livro = repository.findByIsbn("98999-898");
        livro.ifPresent(System.out::println);
    }
    @Test
    void pesquisaPorTituloEPrecoTest(){
        var preco = BigDecimal.valueOf(220.00);
        List<Livro> lista = repository.findByTituloAndPreco("Conhecendo os misterios", preco);
        lista.forEach(System.out::println);
    }

}