package icesi.edu.datamodel.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    private long id;
    private String name;
    private String nationality;

    @JsonIgnore
    private List<Book> books;
}