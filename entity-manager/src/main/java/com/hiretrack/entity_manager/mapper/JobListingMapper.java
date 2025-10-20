package com.hiretrack.entity_manager.mapper;

import com.hiretrack.entity_manager.dto.JobListingDto;
import com.hiretrack.entity_manager.dto.JobUpdateListingDto;
import com.hiretrack.entity_manager.entity.JobListing;

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
    public static JobListing toEntity(JobUpdateListingDto jobListingDto){
        if (jobListingDto == null)
            return null;
        return JobListing.builder()
                .id(jobListingDto.getId())
                .jobProfile(jobListingDto.getJobProfile())
                .jobDescription(jobListingDto.getJobDescription())
                .sourceId(jobListingDto.getSourceId())
                .events(jobListingDto.getEvents())
                .minCGPA(jobListingDto.getMinCGPA())
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
    public static JobUpdateListingDto toDto(JobListing jobListing){
        if (jobListing==null){
            return null;
        }
        return  JobUpdateListingDto.builder()
                .id(jobListing.getId())
                .jobProfile(jobListing.getJobProfile())
                .jobDescription(jobListing.getJobDescription())
                .sourceId(jobListing.getSourceId())
                .events(jobListing.getEvents())
                .minCGPA(jobListing.getMinCGPA())
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
