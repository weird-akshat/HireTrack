package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.JobUpdateDto;
import com.hiretrack.entity_manager.entity.JobListing;
import com.hiretrack.entity_manager.repo.JobListingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LinkingService {
    private final JobListingRepo jobListingRepo;
    public JobListing findJobListing(JobUpdateDto jobUpdateDto){
        //find the company name
        List<JobListing> jobListings = jobListingRepo.findByCompanyNameContaining(jobUpdateDto.getCompanyName());
        if (jobListings.size()==1){
            System.out.println(jobListings.get(0));
            return jobListings.get(0);
        }
        else if (jobUpdateDto.getJobRole()==null){
            return jobListings.stream().max(Comparator.comparing(jobListing -> jobListing.getCreatedAt())).get();
        }
        else{
            jobListings= jobListings.stream().filter(jobListing -> jobListing.getJobProfile().contains(jobUpdateDto.getJobRole())).collect(Collectors.toList());
            if (jobListings.size()==1){
                return jobListings.get(0);
            }
            else {
                return jobListings.stream().max(Comparator.comparing(jobListing -> jobListing.getCreatedAt())).get();
            }

        }

    }
}
