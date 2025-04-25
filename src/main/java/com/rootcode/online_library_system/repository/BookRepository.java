package com.rootcode.online_library_system.repository;

import com.rootcode.online_library_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByPublishedYear(Integer year);
    List<Book> findByAvailableCopiesGreaterThan(Integer copies);
}
