package com.rootcode.online_library_system.repository;

import com.rootcode.online_library_system.model.BorrowRecord;
import com.rootcode.online_library_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUserAndReturnedFalse(User user);
    List<BorrowRecord> findByUser(User user);
}
