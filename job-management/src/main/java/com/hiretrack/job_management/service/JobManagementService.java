package com.hiretrack.job_management.service;

import com.hiretrack.job_management.repo.JobListingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobManagementService {

    private final JobListingRepo jobListingRepo;


}
