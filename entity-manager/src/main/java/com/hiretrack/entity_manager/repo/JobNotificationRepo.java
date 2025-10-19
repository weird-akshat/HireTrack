package com.hiretrack.entity_manager.repo;

import com.hiretrack.entity_manager.entity.JobNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobNotificationRepo extends JpaRepository<JobNotification,Long> {

}
