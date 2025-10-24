package com.hiretrack.message_extractor.controller;

import com.hiretrack.message_extractor.dtos.ApiResponse;
import com.hiretrack.message_extractor.service.MessageInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/info")
@RequiredArgsConstructor
@Slf4j
public class MessageInfoController {
    private final MessageInfoService messageInfoService;
    @PostMapping
    public ResponseEntity<ApiResponse>  findIfExists(@RequestBody Long sourceId){

        try{
            log.info("Request received to check if the source message exists, sourceId: {}", sourceId);
            messageInfoService.messageExists(sourceId);
            log.info("Found message");
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "SourceId exists"), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Source Id not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
