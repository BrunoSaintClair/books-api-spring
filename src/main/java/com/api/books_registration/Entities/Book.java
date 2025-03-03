package com.api.books_registration.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne @JoinColumn(name = "author_id")
    private Author author;
    private String description;
    private Integer numberOfPages;
    private LocalDateTime createdAt;

    public Book(){}

    public Book(String name, Author author, String description, Integer numberOfPages) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.numberOfPages = numberOfPages;
        this.createdAt = LocalDateTime.now();
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

}
