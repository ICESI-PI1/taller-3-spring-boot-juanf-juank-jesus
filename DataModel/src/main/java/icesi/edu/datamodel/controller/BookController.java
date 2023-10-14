package icesi.edu.datamodel.controller;

import icesi.edu.datamodel.persistence.model.Book;
import icesi.edu.datamodel.service.BookService;
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

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Book not found"
        ));
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        bookService.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (!bookService.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book with id " + id + " not found."
            );
        }
        bookService.update(id, book);
        return bookService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Book not found after update"
        ));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        if (!bookService.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book with id " + id + " not found."
            );
        }
        bookService.delete(id);
    }
}