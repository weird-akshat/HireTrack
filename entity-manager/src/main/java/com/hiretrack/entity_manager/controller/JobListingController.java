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
        log.info("JobListingDto: {}",jobListingDto.toString());
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
        log.info("Request to Update Job Listing received.");
        log.info("JobUpdateListingDto: {}",jobUpdateListingDto.toString());
        try{
            jobListingService.updateJobListing(jobUpdateListingDto);

            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"Job updated successfully") ,HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Caught exception: "+ e+ "in update job listing");
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Error in updating job entry."),HttpStatus.BAD_REQUEST);
        }

    }

}
