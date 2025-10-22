package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.JobListingDto;
import com.hiretrack.entity_manager.dto.JobUpdateListingDto;
import com.hiretrack.entity_manager.entity.JobListing;
import com.hiretrack.entity_manager.mapper.JobListingMapper;
import com.hiretrack.entity_manager.repo.JobListingRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobListingService {
    private final JobListingRepo jobListingRepo;
    public JobListingService(JobListingRepo jobListingRepo){
        this.jobListingRepo= jobListingRepo;
    }
    public void createJobListing(JobListingDto jobListingDto){

        try{
            log.info("Mapping JobListingDto to JobListing");
            JobListing jobListing = JobListingMapper.toEntity(jobListingDto);
            log.info("Mapped JobListing: "+jobListing.toString());
            log.info("Trying to save JobListing");
            jobListingRepo.save(jobListing);
            log.info("Saved jobListing");
        }
        catch (Exception e){
            log.error("Error in either mapping or saving");
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
