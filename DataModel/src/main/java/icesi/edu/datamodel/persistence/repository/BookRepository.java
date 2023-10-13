package icesi.edu.datamodel.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import icesi.edu.datamodel.persistence.model.Book;

public class BookRepository implements BookRepositoryI{

    private List<Book> books;

    public BookRepository(){
        books = new ArrayList<>();
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
        return books.add(book);
    }

    @Override
    public boolean update(long id, Book newBook) {
        Optional<Book> opt = findById(id);

        if(opt.isPresent()){
            delete(opt.get().getId());
            return add(newBook);
        } else{
            return add(opt.get());
        }
    }

    @Override
    public boolean delete(long id) {
       return books.remove(findById(id).get());
    }
    
}
