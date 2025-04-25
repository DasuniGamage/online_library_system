package com.rootcode.online_library_system.service.serviceIMPL;

import com.rootcode.online_library_system.dto.BookDto;
import com.rootcode.online_library_system.model.Book;
import com.rootcode.online_library_system.repository.BookRepository;
import com.rootcode.online_library_system.service.BookService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceIMPL implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public Book addBook(@Valid BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublishedYear(bookDto.getPublishedYear());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return bookRepository.save(book);
    }

    public List<Book> searchBooks(String title, String author, Integer year) {
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        } else if (year != null) {
            return bookRepository.findByPublishedYear(year);
        }
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableCopiesGreaterThan(0);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
    @Transactional
    public void updateBookAvailability(Long bookId, int change) {
        Book book = getBookById(bookId);
        int newCopies = book.getAvailableCopies() + change;
        if (newCopies < 0) {
            throw new RuntimeException("No copies available");
        }
        book.setAvailableCopies(newCopies);
        bookRepository.save(book);
    }

}
