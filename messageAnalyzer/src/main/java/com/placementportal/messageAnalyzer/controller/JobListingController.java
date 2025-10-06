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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/job-listings")
public class JobListingController {

    private final JobListingService jobListingService;
    private final JobListingRepo jobListingRepo;
    private final JobListingMapper mapper;
    public JobListingController(JobListingService jobListingService, JobListingRepo jobListingRepo, JobListingMapper jobListingMapper) {
         this.jobListingService= jobListingService;
         this.jobListingRepo= jobListingRepo;
         this.mapper= jobListingMapper;


    }


    @GetMapping
    public ResponseEntity<List<JobListingResponse>> getJobListings(){
        List<JobListing> allJobs = jobListingRepo.findAll();
        List<JobListingResponse> list = allJobs.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());


        return new ResponseEntity<>(list,HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<JobListingResponse> postJobListing(@RequestBody JobListingRequest reqBody){
        String string = reqBody.text();

        try{
            JobListing response = jobListingService.createJobListing(string);

            JobListingResponse res = mapper.toDto(response);


            return new ResponseEntity<>(res,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }




    }
}
