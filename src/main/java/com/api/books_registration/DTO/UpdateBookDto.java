package com.api.books_registration.DTO;

import com.api.books_registration.Entities.Author;
import jakarta.validation.constraints.*;

public record UpdateBookDto(
        @Size(max = 255, message = "Field 'name' must be at most 255 characters.")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Field 'name' contains invalid characters.")
        String name,

        Author author,

        @Size(max = 255, message = "Field 'description' must be at most 255 characters.")
        String description,

        @Min(value = 1, message = "Field 'numberOfPages' must be greater than zero.")
        @Max(value = 10000, message = "Field 'numberOfPages' must be at most 10,000.")
        Integer numberOfPages
) {}
