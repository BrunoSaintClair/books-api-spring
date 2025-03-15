package com.api.books_registration.Services;

import com.api.books_registration.DTO.CreateAuthorDto;
import com.api.books_registration.DTO.UpdateAuthorDto;
import com.api.books_registration.Entities.Author;
import com.api.books_registration.Entities.Book;
import com.api.books_registration.Exceptions.AuthorNotFoundException;
import com.api.books_registration.Exceptions.InvalidFieldException;
import com.api.books_registration.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public Author addAuthor(CreateAuthorDto createAuthorDto) {
        if (repository.findAuthorByName(createAuthorDto.name()) != null) {
            throw new InvalidFieldException("Author's name must be UNIQUE.");
        }

        Author author = new Author(
                createAuthorDto.name().trim()
        );

        return repository.save(author);
    }

    public void deleteAuthorById(Long id){
        if (!repository.existsById(id)){
            throw new AuthorNotFoundException(id);
        }

        Optional <Author> author = repository.findById(id);

        if (author.isPresent()){
            if (!author.get().getBooksList().isEmpty()) {
                throw new InvalidFieldException("Cannot delete an author with associated books.");
            }
        } else {
            throw new AuthorNotFoundException(id);
        }

        repository.deleteById(id);
    }


    public Author getAuthorById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public Author updateAuthorById(Long id, UpdateAuthorDto updateAuthorDto) {
        if (updateAuthorDto.name() == null){ throw new InvalidFieldException("Field 'name' must be provided."); }
        if (!repository.existsById(id)){ throw new AuthorNotFoundException(id); }

        Author authorEntity = repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        authorEntity.setName(updateAuthorDto.name().trim());

        repository.save(authorEntity);
        return authorEntity;
    }

    public List<Book> getBooksByAuthorId(Long id) {
        if (!repository.existsById(id)){ throw new AuthorNotFoundException(id); }
        Author author = repository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return author.getBooksList();
    }

    public Author getAuthorByName(String name){
        Author author = repository.findAuthorByName(name);
        if (author == null){ throw new AuthorNotFoundException("Author with name: '" + name + "' not found."); }
        return author;
    }
}
