package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.JobUpdateDto;
import com.hiretrack.entity_manager.dto.JobUpdateListingDto;
import com.hiretrack.entity_manager.service.LinkingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/link")
@RequiredArgsConstructor
@Slf4j
public class LinkingController {
    private final LinkingService linkingService;
    @PostMapping
    public ResponseEntity<JobUpdateListingDto> updateJob(@RequestBody JobUpdateDto jobUpdateDto){
        try{
            log.info("Request to link an from message analyzer received");
            log.info("JobUpdateDto: {}", jobUpdateDto.toString());
            JobUpdateListingDto jobUpdateListingDto = linkingService.findJobListing(jobUpdateDto);
            log.info("Job Updated Successfully");
            return new ResponseEntity<>(jobUpdateListingDto, HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Couldn't link the job update dto");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
