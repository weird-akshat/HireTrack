package com.hiretrack.entity_manager.repo;

import com.hiretrack.entity_manager.entity.JobNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface JobNotificationRepo extends JpaRepository<JobNotification,Long> {
    @Modifying
    void deleteBySourceId(Long sourceId);
}
