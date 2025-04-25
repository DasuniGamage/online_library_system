package com.rootcode.online_library_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Published year is required")
    private Integer publishedYear;

    @Min(value = 0, message = "Available copies must be greater than or equal to 0")
    private Integer availableCopies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Author is required") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author is required") String author) {
        this.author = author;
    }

    public @NotNull(message = "Published year is required") Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(@NotNull(message = "Published year is required") Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public @Min(value = 0, message = "Available copies must be greater than or equal to 0") Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(@Min(value = 0, message = "Available copies must be greater than or equal to 0") Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public BookDto() {
    }

    public BookDto(Long id, String title, String author, Integer publishedYear, Integer availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.availableCopies = availableCopies;
    }
}
