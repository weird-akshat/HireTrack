package com.hiretrack.job_management.controller;


import com.hiretrack.job_management.dto.JobListingDto;
import com.hiretrack.job_management.dto.JobNotificationDto;
import com.hiretrack.job_management.dto.ShortlistDto;
import com.hiretrack.job_management.entity.JobListing;
import com.hiretrack.job_management.entity.JobNotification;
import com.hiretrack.job_management.entity.Shortlist;
import com.hiretrack.job_management.repo.JobListingRepo;
import com.hiretrack.job_management.service.JobManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
@Slf4j
public class JobController {
    private final JobManagementService jobManagementService;
    @GetMapping
    public ResponseEntity<List<JobListingDto>> getJobListings(){
        try {
            log.info("Got request to get all job listings");
            List<JobListingDto> jobListings= jobManagementService.getJobListings();
            return new ResponseEntity<>(jobListings,HttpStatus.OK);
        }
       catch (Exception e){
            log.error("Issue in getting job listings");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
    @GetMapping("/notification")
    public ResponseEntity<List<JobNotificationDto>> getJobNotifications(){
        try {
            return new ResponseEntity<>(jobManagementService.getAllJobNotifications(),HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Error in getting notification");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/notification/{jobListingId}")
    public ResponseEntity<List<JobNotificationDto>> getJobListingNotification(@PathVariable long jobListingId){
        try {
            return new ResponseEntity<>(jobManagementService.getJobNotifications(jobListingId),HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Error in getting specific notification");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/shortlist/{jobListingId}")
    public ResponseEntity<List<ShortlistDto>> getShortlists(@PathVariable long jobListingId){
        try {
            return new ResponseEntity<>(jobManagementService.getShortlists(jobListingId),HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Error in getting shortlists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
