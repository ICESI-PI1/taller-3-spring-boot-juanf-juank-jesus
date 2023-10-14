package icesi.edu.datamodel.persistence.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Repository;

import icesi.edu.datamodel.persistence.model.Book;

@Repository
public class BookRepository implements BookRepositoryI {

    private List<Book> books;
    private Random random;

    public BookRepository() {
        books = new ArrayList<>();
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
    public List<Book> getAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(long id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public boolean add(Book book) {
        if (book != null) {
            book.setId(generateUniqueId());
            return books.add(book);
        }
        return false;
    }

    @Override
    public boolean update(long id, Book newBook) {
        Optional<Book> opt = findById(id);

        if (opt.isPresent()) {
            Book currentBook = opt.get();

            if (newBook.getTitle() != null) {
                currentBook.setTitle(newBook.getTitle());
            }

            if (newBook.getPublicationDate() != null) {
                currentBook.setPublicationDate(newBook.getPublicationDate());
            }

            if (newBook.getAuthor() != null) {
                currentBook.setAuthor(newBook.getAuthor());
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}