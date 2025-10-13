package com.hiretrack.message_extractor.Repo;

import com.hiretrack.message_extractor.Entity.SourceMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceMessageRepo extends JpaRepository<SourceMessage, Long> {
}
