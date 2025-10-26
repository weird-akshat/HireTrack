package com.hiretrack.job_management.mapper;



import com.hiretrack.job_management.dto.JobListingDto;
import com.hiretrack.job_management.entity.JobListing;

import java.time.LocalDateTime;

public class JobListingMapper {
    public static JobListingDto toDto(JobListing jobListing){

        if (jobListing==null){
            return null;
        }
        return  JobListingDto.builder()
                .id(jobListing.getId())
                .jobProfile(jobListing.getJobProfile())
                .jobDescription(jobListing.getJobDescription())
                .sourceId(jobListing.getSourceId())
                .events(jobListing.getEvents())
                .minCGPA(jobListing.getMinCGPA())
                .dateOT(jobListing.getDateOT())
                .dateInterview(jobListing.getDateInterview())
                .percentageDiploma(jobListing.getPercentageDiploma())
                .percentage10th(jobListing.getPercentage10th())
                .percentage12th(jobListing.getPercentage12th())
                .accommodationAllowance(jobListing.getAccommodationAllowance())
                .deadline(jobListing.getDeadline())
                .offerType(jobListing.getOfferType())
                .category(jobListing.getCategory())
                .stipend(jobListing.getStipend())
                .companyName(jobListing.getCompanyName())
                .eligibilityCriteria(jobListing.getEligibilityCriteria())
                .eligibleBranches(jobListing.getEligibleBranches())
                .onConversionCTC(jobListing.getOnConversionCTC())
                .selectionProcess(jobListing.getSelectionProcess())
                .workLocation(jobListing.getWorkLocation())
                .registrationLinks(jobListing.getRegistrationLinks())
                .build();
    }
}