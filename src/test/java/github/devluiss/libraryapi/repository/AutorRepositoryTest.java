package github.devluiss.libraryapi.repository;

import github.devluiss.libraryapi.model.Autor;
import github.devluiss.libraryapi.model.GeneroLivro;
import github.devluiss.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Lucas");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2002, 1, 20));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        int id = 2;
        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isEmpty()) {
            System.out.println("Nenhum autor encontrado!");
        } else {
            System.out.println("DADOS DO AUTOR:");
            System.out.println(possivelAutor.get());

            Autor autorEcontrado = possivelAutor.get();

            autorEcontrado.setNome("TUTU");
            repository.save(autorEcontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> autoresList = repository.findAll();
        autoresList.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deleteTestId(){
        int id = 2;
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        int id = 4;
        var autorD = repository.findById(id).get();
        repository.delete(autorD);
    }

    @Test
    void salvarAutorComLivrosTest(){

        Autor autor = new Autor();
        autor.setNome("amanda");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1980, 4, 12));

        Livro livro = new Livro();
        livro.setIsbn("989123-898");
        livro.setPreco(BigDecimal.valueOf(220));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Conhecendo os misterios da ciencia");
        livro.setDataPublicacao(LocalDate.of(2002, 3, 12));
        livro.setAutor(autor);


        Livro livro2 = new Livro();
        livro2.setIsbn("989123-898");
        livro2.setPreco(BigDecimal.valueOf(220));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("Tutu o caçador de bruxas");
        livro2.setDataPublicacao(LocalDate.of(2002, 3, 12));
        livro2.setAutor(autor);

        Livro livro3 = new Livro();
        livro3.setIsbn("989123-898");
        livro3.setPreco(BigDecimal.valueOf(220));
        livro3.setGenero(GeneroLivro.MISTERIO);
        livro3.setTitulo("Conhecendo os misterios");
        livro3.setDataPublicacao(LocalDate.of(2002, 3, 12));
        livro3.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);
        autor.getLivros().add(livro3);

        repository.save(autor);
        //livroRepository.saveAll(autor.getLivros()); -> se não estiver em cascata usa-se o save all de livros

    }

    @Test
    void listarLivrosAutor(){
        int id = 16;
        var autor = repository.findById(id).get();

        //buscar os livros do autor
        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);

    }



}
