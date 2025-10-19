package com.hiretrack.message_analyzer.message_analyzer.client;

import com.hiretrack.message_analyzer.message_analyzer.dto.JobListing;
import com.hiretrack.message_analyzer.message_analyzer.dto.JobNotification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="ENTITY-MANAGER")
public interface EntityManagerClient {
    @PostMapping("/api/job-listing")
    void createJobListing(JobListing jobListing);
    @PostMapping("/api/job-notification")
    void createNotification(JobNotification jobNotification);
}
