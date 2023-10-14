package icesi.edu.datamodel.controller;

import icesi.edu.datamodel.persistence.model.Author;
import icesi.edu.datamodel.persistence.model.Book;
import icesi.edu.datamodel.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
@RestController
@RequestMapping("/autores")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Author not found"
        ));
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        if (!authorService.update(id, author)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Unable to update. Author with id " + id + " not found."
            );
        }
        return authorService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Author not found after update"
        ));
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        if (!authorService.delete(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Unable to delete. Author with id " + id + " not found."
            );
        }
    }

    @GetMapping("/{id}/libros")
    public List<Book> getBooksByAuthor(@PathVariable Long id) {
        return authorService.findBooksByAuthor(id);
    }
}