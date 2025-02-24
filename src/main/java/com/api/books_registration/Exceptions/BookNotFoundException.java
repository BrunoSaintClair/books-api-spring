package com.api.books_registration.Exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book with id '" + id + "' not found.");
    }
    public BookNotFoundException() { super(); }
}
