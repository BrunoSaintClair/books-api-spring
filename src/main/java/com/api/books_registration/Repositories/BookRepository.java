package com.api.books_registration.Repositories;

import com.api.books_registration.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findBooksByAuthor(String author);
    Book findBookByName(String name);
}
