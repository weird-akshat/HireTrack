package com.hiretrack.message_extractor.Repo;

import com.hiretrack.message_extractor.Entity.SourceMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SourceMessageRepo extends JpaRepository<SourceMessage, Long> {
}
