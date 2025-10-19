package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.JobUpdateDto;
import com.hiretrack.entity_manager.service.LinkingService;
import lombok.RequiredArgsConstructor;
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
    public void updateJob(@RequestBody JobUpdateDto jobUpdateDto){
        System.out.println("Entered");
        linkingService.findJobListing(jobUpdateDto);
    }

}
