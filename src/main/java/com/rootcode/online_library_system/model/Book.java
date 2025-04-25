package com.rootcode.online_library_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Long id;

    @Column(name = "title" ,length=50, nullable = false)
    private String title;

    @Column(name = "author" ,length=50, nullable = false)
    private String author;

    @Column(name = "published_year" , nullable = false)
    private Integer publishedYear;

    @Column(name = "available_copies" ,length=50, nullable = false)
    @Min(0)
    private Integer availableCopies;

    @OneToMany(mappedBy = "book")
    private Set<BorrowRecord> borrowRecords = new HashSet<>();

    public Book() {
    }

    public Book(Long id, String title, String author, Integer publishedYear, Integer availableCopies, Set<BorrowRecord> borrowRecords) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.availableCopies = availableCopies;
        this.borrowRecords = borrowRecords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public @Min(0) Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(@Min(0) Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Set<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(Set<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }
}
