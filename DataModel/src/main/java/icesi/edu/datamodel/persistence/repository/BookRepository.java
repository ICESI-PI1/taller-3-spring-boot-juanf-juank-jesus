package icesi.edu.datamodel.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Repository;

import icesi.edu.datamodel.persistence.model.Book;

@Repository
public class BookRepository implements BookRepositoryI{

    private List<Book> books;
    private Random random;

    public BookRepository(){
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
        if(book != null) {
            book.setId(generateUniqueId());
            return books.add(book);
        }
        return false;
    }

    @Override
    public boolean update(long id, Book newBook) {
        Optional<Book> opt = findById(id);

        if(opt.isPresent()){
            delete(opt.get().getId());
            newBook.setId(id);
            return add(newBook);
        } else{
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        return books.remove(findById(id).orElse(null));
    }
}