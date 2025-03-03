package com.api.books_registration.Exceptions;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
    public AuthorNotFoundException(Long id) { super("Author with id '" + id + "' not found.");}
}
