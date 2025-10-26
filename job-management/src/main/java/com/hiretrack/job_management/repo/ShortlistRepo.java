package com.hiretrack.job_management.repo;

import com.hiretrack.job_management.entity.Shortlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortlistRepo extends JpaRepository<Shortlist, Long> {
    List<Shortlist> findByJobListingId(Long jobListingId);
}
