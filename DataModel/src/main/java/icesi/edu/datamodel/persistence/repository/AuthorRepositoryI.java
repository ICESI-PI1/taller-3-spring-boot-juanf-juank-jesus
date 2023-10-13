package icesi.edu.datamodel.persistence.repository;

import java.util.List;
import java.util.Optional;

import icesi.edu.datamodel.persistence.model.Author;
import icesi.edu.datamodel.persistence.model.Book;

public interface AuthorRepositoryI{
    
    public List<Author> getAll();
    public Optional<Author> findById(long id);
    public boolean add(Author author);
    public boolean update(long id, Author newAuthor);
    public boolean delete(long id);
    public List<Book> getBooks(long id);

}
