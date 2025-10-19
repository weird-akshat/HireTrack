package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.JobUpdateDto;
import com.hiretrack.entity_manager.service.LinkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LinkingController {
    private final LinkingService linkingService;

    public void updateJob(JobUpdateDto jobUpdateDto){

        linkingService.findJobListing(jobUpdateDto);
    }

}
