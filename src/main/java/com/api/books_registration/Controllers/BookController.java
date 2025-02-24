package com.api.books_registration.Controllers;

import com.api.books_registration.DTO.UpdateUserDto;
import com.api.books_registration.Entities.Book;
import com.api.books_registration.Exceptions.BookNotFoundException;
import com.api.books_registration.Services.BookService;
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
    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = service.addBook(book);
        return ResponseEntity.created(URI.create("api/v1/book/" + createdBook.getId())).body(createdBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") Long bookId){
        try {
            service.deleteBookById(bookId);
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBookById(@PathVariable("id") Long bookId,
                                               @RequestBody UpdateUserDto updateUserDto){
        try {
            service.updateBookById(bookId, updateUserDto);
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long bookId){
        try {
            Book foundBook = service.getBookById(bookId);
            return ResponseEntity.ok(foundBook);
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable("author") String authorName){
        return service.getBooksByAuthor(authorName);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Book> getBookByName(@PathVariable("name") String bookName){
        try {
            Book foundBook = service.getBookByName(bookName);
            return ResponseEntity.ok(foundBook);
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

