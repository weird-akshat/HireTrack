package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.JobNotificationDto;
import com.hiretrack.entity_manager.dto.JobUpdateDto;
import com.hiretrack.entity_manager.dto.JobUpdateListingDto;
import com.hiretrack.entity_manager.entity.JobListing;
import com.hiretrack.entity_manager.mapper.JobListingMapper;
import com.hiretrack.entity_manager.repo.JobListingRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LinkingService {
    private final JobListingRepo jobListingRepo;
    public JobUpdateListingDto findJobListing(JobUpdateDto jobUpdateDto){

       return JobListingMapper.toDto(findJobListing(jobUpdateDto.getCompanyName(),jobUpdateDto.getJobRole()));
    }
    public JobListing findJobListing(JobNotificationDto jobNotificationDto){
        return findJobListing(jobNotificationDto.getCompanyName(),jobNotificationDto.getJobRole());
    }
    public JobListing findJobListing(String companyName, String jobRole){
        log.info("Recieved service request to find the job listing");
        String[] possibleNames= companyName.split(" ");
        companyName= possibleNames[0];
        List<JobListing> jobListings = jobListingRepo.findByCompanyNameContainingIgnoreCase(companyName);


        if (jobListings.isEmpty()){
            log.error("No job listing found");
            throw new RuntimeException("No job found");
        }
        else if (jobListings.size()==1){
            return (jobListings.get(0));
        }
        else if (jobRole.equalsIgnoreCase("")){
            return jobListings.stream().max(Comparator.comparing(JobListing::getCreatedAt)).orElseThrow(()->new RuntimeException("If this gets thrown then what the fuck, issue in jobRole in linking service"));
        }
        else{
            jobListings= jobListings.stream().filter(jobListing -> jobListing.getJobProfile().contains(jobRole)).collect(Collectors.toList());

            if (jobListings.size()==1){
                return (jobListings.get(0));
            }
            else {
                return (jobListings.stream().max(Comparator.comparing(JobListing::getCreatedAt)).orElseThrow(()-> new RuntimeException("If this gets thrown")));
            }

        }
    }
}
