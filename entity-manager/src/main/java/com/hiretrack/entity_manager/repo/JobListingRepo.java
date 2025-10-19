package com.hiretrack.entity_manager.repo;

import com.hiretrack.entity_manager.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobListingRepo extends JpaRepository<JobListing, Long> {
    List<JobListing> findByCompanyNameContaining(String companyName);
}
