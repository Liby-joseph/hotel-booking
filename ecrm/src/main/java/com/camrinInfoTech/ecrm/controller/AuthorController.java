package com.camrinInfoTech.ecrm.controller;

import com.camrinInfoTech.ecrm.model.Author;
import com.camrinInfoTech.ecrm.model.Book;
import com.camrinInfoTech.ecrm.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired

    private AuthorService as;

    @GetMapping("/lists/")
    public void getList() {
        as.CreateAuthorWithBooks();
        List<Book> books = as.getAllBooks();

// Iterate over authors and filter their books
        for (Author author : as.getAllAuthors()) {
            // Filter books that belong to the current author
            List<Book> authorBooks = books.stream()
                    .filter(book -> book.getAuthor().equals(author))
                    .toList();

            // Print author details
//            System.out.println(author);

            // Print details of books for this author
            authorBooks.forEach(System.out::println);
        }
    }
}
