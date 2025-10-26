package com.hiretrack.job_management.repo;

import com.hiretrack.job_management.dto.JobNotificationDto;
import com.hiretrack.job_management.entity.JobNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface JobNotificationRepo extends JpaRepository<JobNotification,Long> {
    List<JobNotification> findByJobListingId(Long jobListingId);
}
