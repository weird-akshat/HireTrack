package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.JobNotificationDto;
import com.hiretrack.entity_manager.dto.JobUpdateDto;
import com.hiretrack.entity_manager.dto.JobUpdateListingDto;
import com.hiretrack.entity_manager.entity.JobListing;
import com.hiretrack.entity_manager.mapper.JobListingMapper;
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
    public JobUpdateListingDto findJobListing(JobUpdateDto jobUpdateDto){

       return JobListingMapper.toDto(findJobListing(jobUpdateDto.getCompanyName(),jobUpdateDto.getJobRole()));
    }
    public JobListing findJobListing(JobNotificationDto jobNotificationDto){
        return findJobListing(jobNotificationDto.getCompanyName(),jobNotificationDto.getJobRole());
    }
    public JobListing findJobListing(String companyName, String jobRole){
        System.out.println(companyName);
        String[] possibleNames= companyName.split(" ");
        System.out.println(possibleNames[0]);
        companyName= possibleNames[0];
        List<JobListing> jobListings = jobListingRepo.findByCompanyNameContainingIgnoreCase(companyName);

        System.out.println(jobListings);
        if (jobListings.isEmpty()){
            return null;
        }
        else if (jobListings.size()==1){
            return (jobListings.get(0));
        }
        else if (jobRole==null){
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
