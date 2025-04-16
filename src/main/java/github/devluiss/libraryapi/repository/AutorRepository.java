package github.devluiss.libraryapi.repository;

import github.devluiss.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Optional<Autor> findById(UUID id);
}
