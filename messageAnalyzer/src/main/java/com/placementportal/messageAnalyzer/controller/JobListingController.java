package com.placementportal.messageAnalyzer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.placementportal.messageAnalyzer.dto.JobListingRequest;
import com.placementportal.messageAnalyzer.dto.JobListingResponse;
import com.placementportal.messageAnalyzer.entity.JobListing;
import com.placementportal.messageAnalyzer.mapper.JobListingMapper;
import com.placementportal.messageAnalyzer.repo.JobListingRepo;
import com.placementportal.messageAnalyzer.service.JobListingService;
import kotlin.collections.ArrayDeque;
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
    public ResponseEntity<List<JobListingResponse>> getJobListings(){
        List<JobListing> allJobs = jobListingRepo.findAll();
        List<JobListingResponse> list= new ArrayDeque<>();
        JobListingMapper mapper = new JobListingMapper();
        for (JobListing jobListing: allJobs){
            list.add((mapper.toDto(jobListing)));
        }

        return new ResponseEntity<>(list,HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<JobListingResponse> postJobListing(@RequestBody JobListingRequest reqBody){
        String string = reqBody.text();

        try{
            JobListing response = jobListingService.createJobListing(string);
            JobListingMapper mapper = new JobListingMapper();
            JobListingResponse res = mapper.toDto(response);


            return new ResponseEntity<>(res,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }




    }
}
