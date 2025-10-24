package com.hiretrack.message_extractor.repo;

import com.hiretrack.message_extractor.entity.SourceMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SourceMessageRepo extends JpaRepository<SourceMessage, Long> {
    List<SourceMessage> findByTimeStamp(LocalDateTime timeStamp);
}
