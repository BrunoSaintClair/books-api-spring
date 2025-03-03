package com.api.books_registration.Controllers;

import com.api.books_registration.DTO.CreateBookDto;
import com.api.books_registration.DTO.UpdateBookDto;
import com.api.books_registration.Entities.Book;
import com.api.books_registration.Services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> findAll(){
        return service.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody @Valid CreateBookDto createBookDto) {
        Book createdBook = service.addBook(createBookDto);
        return ResponseEntity.created(URI.create("api/v1/book/" + createdBook.getId())).body(createdBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id,
                                       @RequestBody @Valid UpdateBookDto updateBookDto){
        Book updatedBook = service.updateBookById(id, updateBookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        Book foundBook = service.getBookById(id);
        return ResponseEntity.ok(foundBook);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Book> findByName(@PathVariable String name){
        Book foundBook = service.getBookByName(name);
        return ResponseEntity.ok(foundBook);
    }
}
