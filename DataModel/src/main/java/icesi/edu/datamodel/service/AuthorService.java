package icesi.edu.datamodel.service;

import icesi.edu.datamodel.persistence.model.Author;
import icesi.edu.datamodel.persistence.model.Book;
import icesi.edu.datamodel.persistence.repository.AuthorRepository;
import icesi.edu.datamodel.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;
    public List<Author> findAll() {
        return authorRepository.getAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    public Author save(Author author) {
        if (authorRepository.add(author)) {
            return author;
        }
        throw new RuntimeException("Error saving the author");
    }

    public boolean update(Long id, Author author) {
        return authorRepository.update(id, author);
    }

    public boolean delete(Long id) {
        Optional<Author> authorToDelete = authorRepository.findById(id);
        if (authorToDelete.isPresent()) {
            List<Book> booksToDelete = authorToDelete.get().getBooks();
            if (booksToDelete != null) {
                for (Book book : booksToDelete) {
                    bookRepository.delete(book.getId());
                }
            }
            return authorRepository.delete(id);
        }
        return false;
    }

    public List<Book> findBooksByAuthor(Long id) {
        return authorRepository.getBooks(id);
    }
}
