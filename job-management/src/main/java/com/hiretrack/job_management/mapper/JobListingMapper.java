package com.hiretrack.job_management.mapper;



import com.hiretrack.job_management.dto.JobListingDto;
import com.hiretrack.job_management.entity.JobListing;

import java.time.LocalDateTime;

public class JobListingMapper {
    public static JobListing toEntity(JobListingDto jobListingDto){
        if (jobListingDto == null)
            return null;
        return JobListing.builder()
                .jobProfile(jobListingDto.getJobProfile())
                .jobDescription(jobListingDto.getJobDescription())
                .sourceId(jobListingDto.getSourceId())
                .events(jobListingDto.getEvents())
                .minCGPA(jobListingDto.getMinCGPA())
                .dateOT(jobListingDto.getDateOT())
                .dateInterview(jobListingDto.getDateInterview())
                .createdAt(LocalDateTime.now())
                .percentageDiploma(jobListingDto.getPercentageDiploma())
                .percentage10th(jobListingDto.getPercentage10th())
                .percentage12th(jobListingDto.getPercentage12th())
                .accommodationAllowance(jobListingDto.getAccommodationAllowance())
                .deadline(jobListingDto.getDeadline())
                .offerType(jobListingDto.getOfferType())
                .category(jobListingDto.getCategory())
                .stipend(jobListingDto.getStipend())
                .companyName(jobListingDto.getCompanyName())
                .eligibilityCriteria(jobListingDto.getEligibilityCriteria())
                .eligibleBranches(jobListingDto.getEligibleBranches())
                .onConversionCTC(jobListingDto.getOnConversionCTC())
                .selectionProcess(jobListingDto.getSelectionProcess())
                .workLocation(jobListingDto.getWorkLocation())
                .registrationLinks(jobListingDto.getRegistrationLinks())
                .build();

    }
}