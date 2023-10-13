package icesi.edu.datamodel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import icesi.edu.datamodel.persistence.model.Book;
import icesi.edu.datamodel.persistence.repository.BookRepository;

@Service
public class BookService implements BookServiceI {

    private BookRepository repository;

    public BookService() {
        repository = new BookRepository();
    }

    @Override
    public List<Book> getAll() {
        return repository.getAll();
    }


    public List<Book> findAll() {
        return getAll();
    }

    @Override
    public Optional<Book> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean add(Book book) {
        return repository.add(book);
    }

    @Override
    public boolean update(long id, Book newBook) {
        return repository.update(id, newBook);
    }

    @Override
    public boolean delete(long id) {
        return repository.delete(id);
    }
}