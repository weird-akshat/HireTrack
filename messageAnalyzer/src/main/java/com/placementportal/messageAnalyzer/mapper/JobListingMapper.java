package com.placementportal.messageAnalyzer.mapper;

import com.placementportal.messageAnalyzer.dto.JobListingResponse;
import com.placementportal.messageAnalyzer.entity.JobListing;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JobListingMapper {

    public JobListingResponse toDto(JobListing jobListing) {
        Map<String, Object> map = new HashMap<>();

        // Simple fields
        map.put("id", jobListing.getId());
        map.put("companyName", jobListing.getCompanyName());
        map.put("offerType", jobListing.getOfferType());
        map.put("category", jobListing.getCategory());
        map.put("jobProfile", jobListing.getJobProfile());
        map.put("jobDescription", jobListing.getJobDescription());
        map.put("stipend", jobListing.getStipend());
        map.put("accommodationAllowance", jobListing.getAccommodationAllowance());
        map.put("onConversionCTC", jobListing.getOnConversionCTC());
        map.put("deadline", jobListing.getDeadline());

        // List fields
        map.put("eligibleBranches", jobListing.getEligibleBranches());
        map.put("eligibilityCriteria", jobListing.getEligibilityCriteria());
        map.put("workLocation", jobListing.getWorkLocation());

        // Map fields
        map.put("events", jobListing.getEvents());
        map.put("selectionProcess", jobListing.getSelectionProcess());
        map.put("registrationLinks", jobListing.getRegistrationLinks());

        return new JobListingResponse(map);
    }
}
