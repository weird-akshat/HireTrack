package com.hiretrack.job_management.controller;


import com.hiretrack.job_management.entity.JobListing;
import com.hiretrack.job_management.repo.JobListingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {
    private final JobListingRepo jobListingRepo;
    @GetMapping
    public List<JobListing> getJobListings(){
        return jobListingRepo.findAll();
    }
}
