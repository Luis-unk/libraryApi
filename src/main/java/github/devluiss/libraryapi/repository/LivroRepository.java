package github.devluiss.libraryapi.repository;

import github.devluiss.libraryapi.model.Autor;
import github.devluiss.libraryapi.model.GeneroLivro;
import github.devluiss.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, Integer>, JpaSpecificationExecutor<Livro> {

    // Querry Method
    // select * form livro where id_autor = id;
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    List<Livro> findByTituloOrPreco(String titulo, BigDecimal preco);

//    @Query("""
//            SELECT l.genero
//            FROM Livro l
//            JOIN l.autor a WHERE
//            a.nacionalidade = `Brasileira`
//            ORDER BY l.genero
//            """)
//    List<String> listarGenerosAutoresBrasileiros();

    @Query("SELECT l FROM Livro l WHERE l.genero = :genero ORDER BY :paramOrdenacao")
    List<Livro> findByGenero(
            @Param("genero")GeneroLivro generoLivro,
            @Param("paramOrdenacao") String nomePropriedade
            );

    @Query("SELECT l FROM Livro l WHERE l.genero = ?1 ORDER BY ?2")
    List<Livro> findByGeneroPositionalParameters(GeneroLivro generoLivro, String nomePropriedade
    );

    @Modifying //sinaliza para o spring que vai ocorrer alguma modificacao no banco
    @Transactional //update , delete, insert deve usar transactional -> commit ou rollback
    @Query("DELETE FROM Livro WHERE genero = ?1")
    void deleteByGenero(GeneroLivro genero);

    boolean existsByAutor(Autor autor);

    Optional<Livro> findById(UUID id);
}
