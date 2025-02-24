package com.api.books_registration.Services;

import com.api.books_registration.DTO.UpdateUserDto;
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

    public void deleteBookById(Long id){
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public void updateBookById(Long id, UpdateUserDto updateUserDto){
        Book bookEntity = repository.findById(id).orElse(null);
        if (bookEntity == null){
            throw new BookNotFoundException(id);
        }
        if (updateUserDto.name() != null){ bookEntity.setName(updateUserDto.name()); }
        if (updateUserDto.description() != null){ bookEntity.setDescription(updateUserDto.description()); }
        if (updateUserDto.author() != null){ bookEntity.setAuthor(updateUserDto.author()); }
        if (updateUserDto.numberOfPages() != null){ bookEntity.setNumberOfPages(updateUserDto.numberOfPages()); }

        repository.save(bookEntity);
    }

    public Book getBookById(Long id){
        Book book = repository.findById(id).orElse(null);
        if (book == null){
            throw new BookNotFoundException(id);
        }
        return book;
    }

    public List<Book> getBooksByAuthor(String author){
        return repository.findBooksByAuthor(author);
    }

    public Book getBookByName(String name){
        Book book = repository.findBookByName(name);
        if (book == null){
            throw new BookNotFoundException();
        }
        return book;
    }
}
