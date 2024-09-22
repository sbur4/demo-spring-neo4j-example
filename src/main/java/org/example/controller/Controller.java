package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Author;
import org.example.model.Book;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController
public class Controller {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/book/{name}")
    public Book createBook(@PathVariable String name) {
//    public Author createBook(@PathVariable String name) {
        Book book = new Book(name, Year.now().getValue());

        Author author = new Author("booker");
//        author.setBooks(Collections.singletonList(book));
        author.setBooks(new ArrayList<>());
        author.getBooks().add(book);
        book.setAuthor(author);

        authorRepository.save(author);
        return bookRepository.save(book);
        //
//        bookRepository.save(book);
//        return authorRepository.save(author);
    }

    @PutMapping("/book/{id}&{title}")
    public Book updateBookById(@PathVariable long id, @PathVariable String title) {
        Book book = bookRepository.findById(id).get();
        book.setTitle(title);

        return bookRepository.save(book);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBookById(@PathVariable long id) {
        bookRepository.deleteById(id);
    }
}