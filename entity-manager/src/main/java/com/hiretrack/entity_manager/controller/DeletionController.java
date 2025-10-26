package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.ApiResponse;
import com.hiretrack.entity_manager.service.DeletionService;
import com.hiretrack.entity_manager.service.JobListingService;
import com.hiretrack.entity_manager.service.JobNotificationService;
import com.hiretrack.entity_manager.service.ShortlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class DeletionController {
    private final DeletionService deletionService;

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteMessage(@RequestBody Long sourceId){
        try{
            log.info("Request to delete received,{}",sourceId);
            deletionService.delete(sourceId);
            log.info("deletion completed");

            return new ResponseEntity<>(new ApiResponse(HttpStatus.NO_CONTENT, "Message deleted"), HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            log.error("Message couldn't be deleted");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}
