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
    private List<Book> books;

    public void addBook(Book book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        this.books.add(book);
    }
}
