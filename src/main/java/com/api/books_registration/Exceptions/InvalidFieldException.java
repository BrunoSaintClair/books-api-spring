package com.api.books_registration.Exceptions;

public class InvalidFieldException extends RuntimeException {
  public InvalidFieldException(String message) {
    super(message);
  }
}
