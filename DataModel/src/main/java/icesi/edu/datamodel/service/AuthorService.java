package icesi.edu.datamodel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.datamodel.persistence.model.Author;
import icesi.edu.datamodel.persistence.model.Book;
import icesi.edu.datamodel.persistence.repository.AuthorRepository;

@Service
public class AuthorService implements AuthorServiceI{

    private AuthorRepository repository;

    public AuthorService(){
        repository = new AuthorRepository();
    }

    @Override
    public List<Author> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<Author> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean add(Author author) {
        return repository.add(author);
    }

    @Override
    public boolean update(long id, Author newAuthor) {
        return repository.update(id, newAuthor);
    }

    @Override
    public boolean delete(long id) {
        return repository.delete(id);
    }

    @Override
    public List<Book> getBooks(long id) {
        return repository.getBooks(id);
    }
    
}
