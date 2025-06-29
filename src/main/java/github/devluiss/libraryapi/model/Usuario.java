package github.devluiss.libraryapi.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String login;

    @Column
    private String senha;

    @Column(unique = true, nullable = false)
    private String email;

    @Type(ListArrayType.class)
    @Column(name = "roles", columnDefinition = "varchar[]")
    private List<String> roles;
}
