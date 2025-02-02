package com.spring.playground.openApi30.service;

import com.spring.playground.openApi30.dto.Book;
import com.spring.playground.openApi30.exception.BookKeepingException;
import com.spring.playground.openApi30.repository.BookRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookKeepingService {

    private final BookRepository bookRepository;


    public void addBook(@NotNull Book book) {
        bookRepository.save(book);
    }

    public Book getBook(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.orElseThrow(() ->
                new BookKeepingException("Book not found with id: " + bookId));

    }

    public List<?> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        bookOptional.ifPresentOrElse(book -> {
            bookRepository.deleteById(bookId);
        }, () -> {
            throw new BookKeepingException("Book not found with id: " + bookId);
        });
    }


    public void updateBook(long bookId, @NotNull Book book) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        bookOptional.ifPresentOrElse(b -> {
            b.setAuthor(book.getAuthor());
            b.setGenre(book.getGenre());
            b.setPrice(book.getPrice());
            b.setPublicationYear(book.getPublicationYear());
            b.setTitle(book.getTitle());
            bookRepository.save(b);

        }, () -> {
            throw new BookKeepingException("Book not found with id: " + bookId);
        });
    }
}
