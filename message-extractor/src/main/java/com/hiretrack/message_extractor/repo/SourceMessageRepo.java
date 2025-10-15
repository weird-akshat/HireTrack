package com.hiretrack.message_extractor.repo;

import com.hiretrack.message_extractor.entity.SourceMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceMessageRepo extends JpaRepository<SourceMessage, Long> {
}
