package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.ApiResponse;
import com.hiretrack.entity_manager.dto.JobListingDto;
import com.hiretrack.entity_manager.dto.JobUpdateListingDto;
import com.hiretrack.entity_manager.service.JobListingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-listing")
@RequiredArgsConstructor
@Slf4j
public class JobListingController {
    private final JobListingService jobListingService;

    @PostMapping
    public ResponseEntity<ApiResponse> createJobListing(@RequestBody JobListingDto jobListingDto){
        log.info("Request to create Job Listing received.");
        try{
            log.info(("Creating job listing."));

            jobListingService.createJobListing(jobListingDto);
            ApiResponse response = new ApiResponse(HttpStatus.CREATED, "Job Listing created successfully");
            return new ResponseEntity<>(response,response.getStatus());
        }
        catch (Exception e){
            ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return new ResponseEntity<>(response,response.getStatus());
        }
    }
    @PutMapping
    public ResponseEntity<ApiResponse> updateJobListing(@RequestBody JobUpdateListingDto jobUpdateListingDto){
        try{
            jobListingService.updateJobListing(jobUpdateListingDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
