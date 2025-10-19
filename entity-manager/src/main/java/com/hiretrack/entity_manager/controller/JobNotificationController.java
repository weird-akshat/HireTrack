package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.JobNotificationDto;
import com.hiretrack.entity_manager.entity.JobNotification;
import com.hiretrack.entity_manager.service.JobNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-notification")
@RequiredArgsConstructor
public class JobNotificationController {
    private final JobNotificationService jobNotificationService;
    @PostMapping
    public ResponseEntity<HttpStatus> createNotification(@RequestBody JobNotificationDto jobNotificationDto){
        try {
            jobNotificationService.createNotification(jobNotificationDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
