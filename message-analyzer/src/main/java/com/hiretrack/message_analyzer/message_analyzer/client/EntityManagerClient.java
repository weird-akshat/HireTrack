package com.hiretrack.message_analyzer.message_analyzer.client;

import com.hiretrack.message_analyzer.message_analyzer.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="ENTITY-MANAGER")
public interface EntityManagerClient {
    @PostMapping("/api/job-listing")
    void createJobListing(JobListing jobListing);
    @PostMapping("/api/job-notification")
    void createNotification(JobNotification jobNotification);

    @PostMapping("/api/link")
    JobUpdateListing link(JobUpdate jobUpdate);

    @PutMapping("/api/job-listing")
    void updateJob(JobUpdateListing jobUpdateListing);

    @PostMapping("/api/shortlist")
    void createShortlist(Shortlist shortlist);
}
