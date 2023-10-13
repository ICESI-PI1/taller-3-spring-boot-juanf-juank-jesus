package icesi.edu.datamodel.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import icesi.edu.datamodel.persistence.model.Author;
import icesi.edu.datamodel.persistence.model.Book;

@Repository
public class AuthorRepository implements AuthorRepositoryI {

    private List<Author> authors;
    private AtomicLong idGenerator; // para generar IDs

    public AuthorRepository() {
        authors = new ArrayList<>();
        idGenerator = new AtomicLong(1); // iniciar contador de ID en 1
    }

    @Override
    public List<Author> getAll() {
        return authors;
    }

    @Override
    public Optional<Author> findById(long id) {
        return authors.stream().filter(a -> a.getId() == id).findFirst();
    }

    @Override
    public boolean add(Author author) {
        if (author != null) {
            author.setId(idGenerator.getAndIncrement()); // asignar ID y aumentar contador
            return authors.add(author);
        }
        return false;
    }

    @Override
    public boolean update(long id, Author newAuthor) {
        Optional<Author> opt = findById(id);

        if (opt.isPresent()) {
            Author existingAuthor = opt.get();
            delete(existingAuthor.getId());
            newAuthor.setId(id); // asignar el mismo ID al nuevo autor
            return add(newAuthor);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        Optional<Author> authorToDelete = findById(id);
        if (authorToDelete.isPresent()) {
            return authors.remove(authorToDelete.get());
        }
        return false;
    }

    @Override
    public List<Book> getBooks(long id) {
        Optional<Author> author = findById(id);
        if (author.isPresent()) {
            return author.get().getBooks();
        }
        return new ArrayList<>(); // retornar lista vacía si el autor no se encuentra
    }
}