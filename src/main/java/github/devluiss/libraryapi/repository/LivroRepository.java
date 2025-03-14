package github.devluiss.libraryapi.repository;

import github.devluiss.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
