package github.devluiss.libraryapi.repository;

import github.devluiss.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
