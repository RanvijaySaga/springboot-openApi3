package com.spring.playground.openApi30.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue
    private long bookId;

    @NotEmpty(message = "Title cannot be null")
    private String title;
    @NotEmpty(message = "Author cannot be null")
    private String author;
    private String genre;
    @Min(value = 1000, message = "Publication year should be greater than 1000")
    private int publicationYear;
    @Min(value = 1, message = "Price cannot be less than 1")
    private double price;

}
