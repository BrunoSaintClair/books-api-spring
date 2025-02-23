package com.api.books_registration.Repositories;

import com.api.books_registration.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
