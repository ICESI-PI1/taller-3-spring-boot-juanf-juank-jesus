package icesi.edu.datamodel.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import icesi.edu.datamodel.persistence.model.Author;
import icesi.edu.datamodel.persistence.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class AuthorRepository implements AuthorRepositoryI {

    @Autowired
    private BookRepository bookRepository;

    private List<Author> authors;
    private Random random;

    public AuthorRepository() {
        authors = new ArrayList<>();
        random = new Random();
    }

    private long generateUniqueId() {
        long id;
        do {
            id = Math.abs(random.nextLong());
        } while (findById(id).isPresent());
        return id;
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
            author.setId(generateUniqueId());
            return authors.add(author);
        }
        return false;
    }

    @Override
    public boolean update(long id, Author newAuthor) {
        Optional<Author> opt = findById(id);

        if (opt.isPresent()) {
            Author existingAuthor = opt.get();

            if (newAuthor.getName() != null) {
                existingAuthor.setName(newAuthor.getName());
            }

            if (newAuthor.getNationality() != null) {
                existingAuthor.setNationality(newAuthor.getNationality());
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        Optional<Author> authorToDelete = findById(id);
        if (authorToDelete.isPresent()) {

            if(authorToDelete.get().getBooks() != null) {
                for(Book book : authorToDelete.get().getBooks()) {
                    bookRepository.delete(book.getId());
                }
            }

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
        return new ArrayList<>();
    }
}