package com.spring.playground.openApi30.controller;

import com.spring.playground.openApi30.dto.Book;
import com.spring.playground.openApi30.service.BookKeepingService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookKeepingController {

    private final BookKeepingService bookKeepingService;

    @GetMapping("/getBookDetails")
    public ResponseEntity<?> getBooks() {
        return new ResponseEntity<>(bookKeepingService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/getBook/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable @Min(value = 1, message = "book id value can not be less than 1") long bookId) {
        return new ResponseEntity<>(bookKeepingService.getBook(bookId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<?> deleteBook(@PathVariable @Min(value = 1, message = "book id value can not be less than 1") long bookId) {
        bookKeepingService.deleteBook(bookId);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody @NotNull(message = "Book can not be null") Book book) {
        bookKeepingService.addBook(book);
        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<?> updateBook(@RequestBody @NotNull(message = "Book can not be null") Book book) {
        bookKeepingService.updateBook(book.getBookId(), new Book());
        return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
    }
}
