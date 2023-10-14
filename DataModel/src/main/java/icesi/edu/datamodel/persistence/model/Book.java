package icesi.edu.datamodel.persistence.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private long id;
    private String title;
    private Date publicationDate;
    private Author author;
}