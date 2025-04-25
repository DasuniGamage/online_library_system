package com.rootcode.online_library_system.service;

import com.rootcode.online_library_system.dto.BookDto;
import com.rootcode.online_library_system.model.Book;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book addBook(@Valid BookDto bookDto);

    List<Book> getAvailableBooks();

    Book getBookById(Long id);

    void updateBookAvailability(Long bookId, int i);
}
