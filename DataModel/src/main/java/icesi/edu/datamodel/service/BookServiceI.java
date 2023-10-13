package icesi.edu.datamodel.service;

import java.util.List;
import java.util.Optional;

import icesi.edu.datamodel.persistence.model.Book;

public interface BookServiceI {
    public List<Book> getAll();
    public Optional<Book> findById(long id);
    public boolean add(Book book);
    public boolean update(long id, Book newBook);
    public boolean delete(long id);
}
