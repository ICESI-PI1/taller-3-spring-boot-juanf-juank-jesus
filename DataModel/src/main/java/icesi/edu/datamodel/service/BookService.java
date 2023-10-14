package icesi.edu.datamodel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icesi.edu.datamodel.persistence.model.Book;
import icesi.edu.datamodel.persistence.model.Author;
import icesi.edu.datamodel.persistence.repository.BookRepository;
import icesi.edu.datamodel.persistence.repository.AuthorRepository;

@Service
public class BookService implements BookServiceI {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    public boolean existsById(long id) {
        return bookRepository.findById(id).isPresent();
    }

    @Override
    public boolean add(Book book) {
        Author author = book.getAuthor();
        if (author != null && !authorRepository.findById(author.getId()).isPresent()) {
            authorRepository.add(author);
        }
        return bookRepository.add(book);
    }

    @Override
    public boolean update(long id, Book newBook) {
        return bookRepository.update(id, newBook);
    }

    @Override
    public boolean delete(long id) {
        return bookRepository.delete(id);
    }
}