package com.rootcode.online_library_system.service;

import com.rootcode.online_library_system.model.BorrowRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BorrowService {
    BorrowRecord borrowBook(Long userId, Long bookId);

    BorrowRecord returnBook(Long userId, Long bookId);

    List<BorrowRecord> getUserBorrowHistory(Long userId);

    List<BorrowRecord> getUserActiveLoans(Long userId);
}
