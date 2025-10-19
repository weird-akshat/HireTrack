package com.hiretrack.entity_manager.repo;

import com.hiretrack.entity_manager.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListingRepo extends JpaRepository<JobListing, Long> {
}
