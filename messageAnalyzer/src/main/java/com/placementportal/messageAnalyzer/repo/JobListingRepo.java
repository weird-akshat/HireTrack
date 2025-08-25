package com.placementportal.messageAnalyzer.repo;

import com.placementportal.messageAnalyzer.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListingRepo extends JpaRepository<JobListing,Long> {
}
