package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.JobUpdateDto;
import com.hiretrack.entity_manager.dto.JobUpdateListingDto;
import com.hiretrack.entity_manager.service.LinkingService;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/link")
@RequiredArgsConstructor
public class LinkingController {
    private final LinkingService linkingService;
    @PostMapping
    public ResponseEntity<JobUpdateListingDto> updateJob(@RequestBody JobUpdateDto jobUpdateDto){
        try{
            JobUpdateListingDto jobUpdateListingDto = linkingService.findJobListing(jobUpdateDto);
            System.out.println("Returning this brother");
            System.out.println(jobUpdateListingDto.toString());
            return new ResponseEntity<>(jobUpdateListingDto, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
