package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.ApiResponse;
import com.hiretrack.entity_manager.dto.JobNotificationDto;
import com.hiretrack.entity_manager.service.JobNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-notification")
@RequiredArgsConstructor
@Slf4j
public class JobNotificationController {
    private final JobNotificationService jobNotificationService;
    @PostMapping
    public ResponseEntity<ApiResponse> createNotification(@RequestBody JobNotificationDto jobNotificationDto){
        log.info("Request to create notification received.");
        log.info("Received JobNotificationDto: {}", jobNotificationDto.toString());
        try {
            jobNotificationService.createNotification(jobNotificationDto);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED, "Notification created"),HttpStatus.CREATED);
        }
        catch (Exception e){

            return new ResponseEntity<>(new ApiResponse( HttpStatus.BAD_REQUEST, "Error in creating notification."),HttpStatus.BAD_REQUEST);
        }
    }
}
