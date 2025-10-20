package com.hiretrack.entity_manager.service;

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

        List<JobListing> jobListings = jobListingRepo.findByCompanyNameContaining(jobUpdateDto.getCompanyName());
        if (jobListings.size()==1){
            System.out.println(jobListings.get(0));
            return JobListingMapper.toDto(jobListings.get(0));
        }
        else if (jobUpdateDto.getJobRole()==null){
            return JobListingMapper.toDto(jobListings.stream().max(Comparator.comparing(jobListing -> jobListing.getCreatedAt())).get());
        }
        else{
            jobListings= jobListings.stream().filter(jobListing -> jobListing.getJobProfile().contains(jobUpdateDto.getJobRole())).collect(Collectors.toList());
            if (jobListings.size()==1){
                return JobListingMapper.toDto(jobListings.get(0));
            }
            else {
                return JobListingMapper.toDto(jobListings.stream().max(Comparator.comparing(jobListing -> jobListing.getCreatedAt())).get());
            }

        }

    }
}
