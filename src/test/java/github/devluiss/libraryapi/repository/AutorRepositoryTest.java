package github.devluiss.libraryapi.repository;

import github.devluiss.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

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
}
