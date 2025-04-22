package github.devluiss.libraryapi.service;

import github.devluiss.libraryapi.exceptions.OperacaoNaoPermitidaException;
import github.devluiss.libraryapi.model.Autor;
import github.devluiss.libraryapi.repository.AutorRepository;
import github.devluiss.libraryapi.repository.LivroRepository;
import github.devluiss.libraryapi.validator.AutorValidator;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    private final AutorValidator validator;

    private final LivroRepository livroRepository;

    public AutorService(AutorRepository repository, AutorValidator validator, LivroRepository livroRepository){
        this.repository = repository;
        this.validator = validator;
        this.livroRepository = livroRepository;
    }

    public Autor salvar(Autor autor){
        validator.validar(autor);
        return repository.save(autor);
    }

    public void atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessários que o autor já esteja salvo.");
        }
        validator.validar(autor);
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Autor autor) {
        if(possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir um Autor que possui livros cadastrados!");
        }
        repository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
        if(nome != null && nacionalidade != null) {
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if(nome != null) {
            return repository.findByNome(nome);
        }

        if(nacionalidade != null){
            return repository.findByNacionalidade(nacionalidade);
        }

        return repository.findAll();
    }

    /*QueryByExampleExecutor -> Executa querys através de examples
    Examples -> utiliza apenas os dados que estão preenchidos em um objeto para executar uma query
    */
    public List<Autor> pesquisaByExample(String nome, String nacionalidade){
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        //Matcher -> Funciona como filtros de pesquisa para o Example
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Autor> autorExample = Example.of(autor, matcher);
        return repository.findAll(autorExample);
    }

    private boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
