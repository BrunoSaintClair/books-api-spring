package com.api.books_registration.Repositories;

import com.api.books_registration.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByName(String name);
}
