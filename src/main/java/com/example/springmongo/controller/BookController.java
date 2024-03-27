package com.example.springmongo.controller;

import com.example.springmongo.model.Book;
import com.example.springmongo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/addBook")
    public void addBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    @GetMapping("/getBook")
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @GetMapping("/getBookById")
    public Book getBookById(@PathVariable("id") String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @PutMapping("/book/{id}")
    public Book updateBook(@PathVariable("id") String id, @RequestBody Book book) {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            Book updateBookData = bookData.get();
            updateBookData.setTitle(book.getTitle());
            updateBookData.setDescription(book.getDescription());
            updateBookData.setPublished(book.isPublished());
            return bookRepository.save(updateBookData);
        }
        return book;
    }

    @DeleteMapping("/book/{id}")
    public void deleteBookById(@PathVariable("id") String id) {
        bookRepository.deleteById(id);
    }

    }
