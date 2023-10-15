package icesi.edu.datamodel.controller;

import icesi.edu.datamodel.persistence.model.Book;
import icesi.edu.datamodel.persistence.model.Author;
import icesi.edu.datamodel.service.BookService;
import icesi.edu.datamodel.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Libro no encontrado"
        ));
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {

        Author existingAuthor = authorService.findById(book.getAuthor().getId()).orElse(null);
        if (existingAuthor == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El autor con el ID " + book.getAuthor().getId() + " no existe."
            );
        }
        book.setAuthor(existingAuthor);
        bookService.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (!bookService.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Libro con ID " + id + " no encontrado."
            );
        }
        bookService.update(id, book);
        return bookService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Libro no encontrado después de actualizar"
        ));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        if (!bookService.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Libro con ID " + id + " no encontrado."
            );
        }
        bookService.delete(id);
    }
}
