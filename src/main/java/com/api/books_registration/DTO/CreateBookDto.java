package com.api.books_registration.DTO;

import jakarta.validation.constraints.*;

public record CreateBookDto(
        @NotBlank(message = "Field 'name' can't be empty.")
        @Size(max = 255, message = "Field 'name' must be at most 255 characters.")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Field 'name' contains invalid characters.")
        String name,

        @NotBlank(message = "Field 'author' can't be empty.")
        @Size(max = 255, message = "Field 'author' must be at most 255 characters.")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Field 'author' must contain only letters and spaces.")
        String author,

        @NotBlank(message = "Field 'description' can't be empty.")
        @Size(max = 255, message = "Field 'description' must be at most 255 characters.")
        String description,

        @NotNull(message = "Field 'numberOfPages' can't be null.")
        @Min(value = 1, message = "Field 'numberOfPages' must be greater than zero.")
        @Max(value = 10000, message = "Field 'numberOfPages' must be at most 10,000.")
        Integer numberOfPages
) {}
