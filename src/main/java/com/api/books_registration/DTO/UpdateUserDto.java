package com.api.books_registration.DTO;

public record UpdateUserDto(String name, String author, String description, Integer numberOfPages) {
}
