package github.devluiss.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "autor")
@Getter
@Setter
@ToString(exclude = "livros")
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_seq")
    @SequenceGenerator(name = "autor_seq", sequenceName = "AUTOR_SEQ", allocationSize = 1)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livro> livros;
}


