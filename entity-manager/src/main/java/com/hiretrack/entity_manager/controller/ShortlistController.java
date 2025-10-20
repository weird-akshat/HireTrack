package com.hiretrack.entity_manager.controller;

import com.hiretrack.entity_manager.dto.ShortlistDto;
import com.hiretrack.entity_manager.service.ShortlistService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
public class ShortlistController {
    private final ShortlistService shortlistService;
    @PostMapping("/shortlist")
    public ResponseEntity<HttpStatus> createShortlist(@RequestBody ShortlistDto shortlistDto){
        try{
            shortlistService.createShortlist(shortlistDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
