package com.rootcode.online_library_system.controller;

import com.rootcode.online_library_system.model.BorrowRecord;
import com.rootcode.online_library_system.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping("/{userId}/borrow/{bookId}")
    public ResponseEntity<BorrowRecord> borrowBook(
            @PathVariable Long userId,
            @PathVariable Long bookId) {
        BorrowRecord record = borrowService.borrowBook(userId, bookId);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/{userId}/return/{bookId}")
    public ResponseEntity<BorrowRecord> returnBook(
            @PathVariable Long userId,
            @PathVariable Long bookId) {
        BorrowRecord record = borrowService.returnBook(userId, bookId);
        return ResponseEntity.ok(record);
    }

    @GetMapping("/{userId}/history")
    public ResponseEntity<List<BorrowRecord>> getBorrowHistory(@PathVariable Long userId) {
        List<BorrowRecord> history = borrowService.getUserBorrowHistory(userId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{userId}/active")
    public ResponseEntity<List<BorrowRecord>> getActiveLoans(@PathVariable Long userId) {
        List<BorrowRecord> activeLoans = borrowService.getUserActiveLoans(userId);
        return ResponseEntity.ok(activeLoans);
    }
}
