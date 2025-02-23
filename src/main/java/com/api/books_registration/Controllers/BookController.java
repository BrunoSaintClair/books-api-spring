package com.api.books_registration.Controllers;

import com.api.books_registration.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService service;
}
