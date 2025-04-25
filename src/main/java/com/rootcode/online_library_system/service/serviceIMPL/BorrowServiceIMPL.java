package com.rootcode.online_library_system.service.serviceIMPL;

import com.rootcode.online_library_system.model.Book;
import com.rootcode.online_library_system.model.BorrowRecord;
import com.rootcode.online_library_system.model.User;
import com.rootcode.online_library_system.repository.BorrowRecordRepository;
import com.rootcode.online_library_system.service.BookService;
import com.rootcode.online_library_system.service.BorrowService;
import com.rootcode.online_library_system.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowServiceIMPL implements BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public BorrowRecord borrowBook(Long userId, Long bookId) {
        User user = userService.getUserByEmail(userId.toString());
        Book book = bookService.getBookById(bookId);
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available for borrowing");
        }

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUser(user);
        borrowRecord.setBook(book);
        borrowRecord.setBorrowDate(LocalDateTime.now());
        borrowRecord.setReturned(false);

        bookService.updateBookAvailability(bookId, -1);
        return borrowRecordRepository.save(borrowRecord);
    }

    @Override
    @Transactional
    public BorrowRecord returnBook(Long userId, Long bookId) {
        User user = userService.getUserByEmail(userId.toString());
        List<BorrowRecord> activeRecords = borrowRecordRepository.findByUserAndReturnedFalse(user);

        BorrowRecord record = activeRecords.stream()
                .filter(r -> r.getBook().getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No active borrow record found"));

        record.setReturnDate(LocalDateTime.now());
        record.setReturned(true);
        bookService.updateBookAvailability(bookId, 1);

        return borrowRecordRepository.save(record);
    }

    @Override
    public List<BorrowRecord> getUserBorrowHistory(Long userId) {
        User user = userService.getUserByEmail(userId.toString());
        return borrowRecordRepository.findByUser(user);
    }

    @Override
    public List<BorrowRecord> getUserActiveLoans(Long userId) {
        User user = userService.getUserByEmail(userId.toString());
        return borrowRecordRepository.findByUserAndReturnedFalse(user);
    }
}
