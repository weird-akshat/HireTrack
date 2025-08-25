package com.placementportal.messageAnalyzer.controller;

import com.placementportal.messageAnalyzer.dto.JobListingRequest;
import com.placementportal.messageAnalyzer.dto.JobListingResponse;
import com.placementportal.messageAnalyzer.entity.JobListing;
import com.placementportal.messageAnalyzer.repo.JobListingRepo;
import com.placementportal.messageAnalyzer.service.JobListingService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/job-listings")
public class JobListingController {

    private final JobListingService jobListingService;
    private final JobListingRepo jobListingRepo;
    public JobListingController(JobListingService jobListingService, JobListingRepo jobListingRepo) {
         this.jobListingService= jobListingService;
         this.jobListingRepo= jobListingRepo;



    }


    @GetMapping
    public ResponseEntity<List<JobListing>> getJobListings(){
        List<JobListing> allJobs = jobListingRepo.findAll();

        return ResponseEntity.ok(allJobs);
    }


    @PostMapping
    public ResponseEntity<JobListingResponse> postJobListing(@RequestBody JobListingRequest reqBody){
        String string = reqBody.text();

        try{
            Map<String, Object> response = jobListingService.createJobListing(string);

            return new ResponseEntity<>(new JobListingResponse(response),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }




    }
}
