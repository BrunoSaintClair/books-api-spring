package com.api.books_registration.Controllers;

import com.api.books_registration.DTO.CreateAuthorDto;
import com.api.books_registration.DTO.UpdateAuthorDto;
import com.api.books_registration.Entities.Author;
import com.api.books_registration.Entities.Book;
import com.api.books_registration.Services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping
    public List<Author> findAll(){
        return service.getAllAuthors();
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody @Valid CreateAuthorDto createAuthorDto){
        Author createdAuthor = service.addAuthor(createAuthorDto);
        return ResponseEntity.created(URI.create("api/v1/author/" + createdAuthor.getId())).body(createdAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") Long id,
                                         @RequestBody UpdateAuthorDto updateAuthorDto){
        Author updatedAuthorDto = service.updateAuthorById(id, updateAuthorDto);
        return ResponseEntity.ok(updatedAuthorDto);
    }

    @GetMapping("/{id}/books")
    public Set<Book> getBooks(@PathVariable("id") Long id){
        Set<Book> books = service.getBooksByAuthorId(id);
        return books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable("id") Long id){
        Author author = service.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Author> findByName(@PathVariable("name") String name){
        Author author = service.getAuthorByName(name);
        return ResponseEntity.ok(author);
    }
}
