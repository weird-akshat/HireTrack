package com.hiretrack.message_analyzer.message_analyzer.client;

import com.hiretrack.message_analyzer.message_analyzer.dto.JobListing;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="ENTITY-MANAGER")
public interface EntityManagerClient {
    @PostMapping("/api/job-listing")
    void create(JobListing jobListing);
}
