package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.JobListingDto;
import com.hiretrack.entity_manager.dto.JobUpdateListingDto;
import com.hiretrack.entity_manager.entity.JobListing;
import com.hiretrack.entity_manager.mapper.JobListingMapper;
import com.hiretrack.entity_manager.repo.JobListingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class JobListingService {
    private final JobListingRepo jobListingRepo;
    public JobListingService(JobListingRepo jobListingRepo){
        this.jobListingRepo= jobListingRepo;
    }
    public void createJobListing(JobListingDto jobListingDto){
        try{
            JobListing jobListing = JobListingMapper.toEntity(jobListingDto);

            jobListingRepo.save(jobListing);
        }
        catch (Exception e){
            throw new RuntimeException("Error in creating jobListing");
        }

    }
    public void updateJobListing(JobUpdateListingDto jobUpdateListingDto){
        try{
//            JobListing jobListing = jobListingRepo.findById(jobUpdateListingDto.getId()).orElseThrow( ()->new RuntimeException("No job listing"));

            JobListing jobListing = JobListingMapper.toEntity(jobUpdateListingDto);
            jobListingRepo.save(jobListing);
        }
        catch (Exception e){
            throw new RuntimeException( "error in updating job listing");
        }
    }

}
