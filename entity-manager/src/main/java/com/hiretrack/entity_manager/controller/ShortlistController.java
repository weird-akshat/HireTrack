package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.ApiResponse;
import com.hiretrack.entity_manager.dto.ShortlistDto;
import com.hiretrack.entity_manager.service.ShortlistService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTML;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ShortlistController {

    private final ShortlistService shortlistService;
    @PostMapping("/shortlist")
    public ResponseEntity<ApiResponse> createShortlist(@RequestBody ShortlistDto shortlistDto){
        try{
            log.info("Request to create shortlist received");
            log.info("ShortlistDto: {}",shortlistDto.toString());
            shortlistService.createShortlist(shortlistDto);
            log.info("Shortlist created successfully");

            return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED, "Shortlist created."),HttpStatus.CREATED);
        }
        catch (Exception e){
            log.info("Error in creating shortlist");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
