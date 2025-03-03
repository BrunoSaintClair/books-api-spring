package com.api.books_registration.Services;

import com.api.books_registration.DTO.CreateBookDto;
import com.api.books_registration.DTO.UpdateBookDto;
import com.api.books_registration.Entities.Author;
import com.api.books_registration.Entities.Book;
import com.api.books_registration.Exceptions.AuthorNotFoundException;
import com.api.books_registration.Exceptions.BookNotFoundException;
import com.api.books_registration.Exceptions.InvalidFieldException;
import com.api.books_registration.Repositories.AuthorRepository;
import com.api.books_registration.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public Book addBook(CreateBookDto createBookDto){
        if (repository.findBookByName(createBookDto.name()) != null) {
            throw new InvalidFieldException("Book's name must be UNIQUE.");
        }

        Author author = createBookDto.author();

        if (!authorRepository.existsById(author.getId())){
            throw new AuthorNotFoundException(author.getId());
        }

        if (authorRepository.findAuthorByName(author.getName()) == null){
            throw new AuthorNotFoundException("Author with name '" + author.getName() + "' not found.");
        }

        Book book = new Book(
                createBookDto.name().trim(),
                author,
                createBookDto.description().trim(),
                createBookDto.numberOfPages()
        );

        return repository.save(book);
    }

    public void deleteBookById(Long id){
        if (!repository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public Book updateBookById(Long id, UpdateBookDto updateBookDto){
        if (updateBookDto.name() == null && updateBookDto.description() == null &&
                updateBookDto.author() == null && updateBookDto.numberOfPages() == null){
            throw new InvalidFieldException("At least one field must be provided.");
        }

        Book bookEntity = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (updateBookDto.name() != null) {
            bookEntity.setName(updateBookDto.name().trim());
        }
        if (updateBookDto.description() != null) {
            bookEntity.setDescription(updateBookDto.description().trim());
        }
        if (updateBookDto.author() != null) {
            bookEntity.setAuthor(updateBookDto.author());
        }
        if (updateBookDto.numberOfPages() != null) {
            bookEntity.setNumberOfPages(updateBookDto.numberOfPages());
        }

        return repository.save(bookEntity);
    }

    public Book getBookById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book getBookByName(String name){
        Book book = repository.findBookByName(name);
        if (book == null) {
            throw new BookNotFoundException(name);
        }
        return book;
    }
}
