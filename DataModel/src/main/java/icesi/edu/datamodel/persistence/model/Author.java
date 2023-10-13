package icesi.edu.datamodel.persistence.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    private long id;
    private String name;
    private String nacionality;
    private List<Book> books;
}
