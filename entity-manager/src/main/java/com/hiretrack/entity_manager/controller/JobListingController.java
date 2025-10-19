package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.JobListingDto;
import com.hiretrack.entity_manager.service.JobListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-listing")
@RequiredArgsConstructor
public class JobListingController {
    private final JobListingService jobListingService;

    @PostMapping
    public ResponseEntity<HttpStatus> createJobListing(@RequestBody JobListingDto jobListingDto){

        try{
            jobListingService.createJobListing(jobListingDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
