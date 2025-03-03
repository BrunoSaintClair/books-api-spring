package com.api.books_registration.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateAuthorDto(
        @NotBlank(message = "Field 'name' can't be empty.")
        @Size(max = 255, message = "Field 'name' must be at most 255 characters.")
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Field 'name' contains invalid characters.")
        String name
) {}
