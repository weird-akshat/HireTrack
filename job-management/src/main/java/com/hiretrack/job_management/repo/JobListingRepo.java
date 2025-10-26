package com.hiretrack.job_management.repo;

import com.hiretrack.job_management.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface JobListingRepo extends JpaRepository<JobListing, Long> {
    List<JobListing> findByCompanyNameContainingIgnoreCase(String companyName);

}
