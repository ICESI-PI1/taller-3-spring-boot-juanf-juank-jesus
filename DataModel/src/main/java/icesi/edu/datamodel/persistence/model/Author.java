package icesi.edu.datamodel.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private long id;
    private String name;
    private String nationality;

    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        book.setAuthor(this);
        this.books.add(book);
    }

    public void removeBook(Book book) {
        if (this.books != null && this.books.contains(book)) {
            book.setAuthor(null);
            this.books.remove(book);
        }
    }

}