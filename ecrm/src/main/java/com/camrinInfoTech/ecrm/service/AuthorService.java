package com.camrinInfoTech.ecrm.service;

import com.camrinInfoTech.ecrm.model.Author;
import com.camrinInfoTech.ecrm.model.Book;
import com.camrinInfoTech.ecrm.repository.AuthorRepository;
import com.camrinInfoTech.ecrm.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional

    public void CreateAuthorWithBooks()
    {
        Author a = new Author();
        a.setName("Jerald");
        a.setEmail("jerald@gmail.com");

        Book b1 = new Book();
        b1.setTitle("C");
        b1.setGenre("Programming");
        b1.setAuthor(a);

        Book b2 = new Book();
        b2.setTitle("Java");
        b2.setGenre("Programming");
        b2.setAuthor(a);

        a.setBook(List.of(b1,b2));
        authorRepository.save(a);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}
