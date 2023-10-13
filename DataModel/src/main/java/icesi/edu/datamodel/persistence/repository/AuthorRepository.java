package icesi.edu.datamodel.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import icesi.edu.datamodel.persistence.model.Author;
import icesi.edu.datamodel.persistence.model.Book;

@Repository
public class AuthorRepository implements AuthorRepositoryI{

    private List<Author> authors;

    public AuthorRepository(){
        authors = new ArrayList<>();
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
        return authors.add(author);
    }

    @Override
    public boolean update(long id, Author newAuthor) {
        Optional<Author> opt = findById(id);

        if(opt.isPresent()){
            delete(opt.get().getId());
            return add(newAuthor);
        } else{
            return add(opt.get());
        }
    }

    @Override
    public boolean delete(long id) {
        return authors.remove(findById(id).get());
    }

    @Override
    public List<Book> getBooks(long id) {
        return findById(id).get().getBooks();
    }
    
}
