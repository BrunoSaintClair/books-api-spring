package com.api.books_registration.Services;

import com.api.books_registration.Entities.Book;
import com.api.books_registration.Exceptions.BookNotFoundException;
import com.api.books_registration.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public Book addBook(Book book){
        return repository.save(book);
    }

    public void deleteBook(Long id){
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
