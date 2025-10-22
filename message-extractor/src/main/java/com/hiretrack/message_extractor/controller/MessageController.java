package com.hiretrack.message_extractor.controller;

import com.hiretrack.message_extractor.dtos.ChunkMessageDTO;
import com.hiretrack.message_extractor.dtos.OutputMessage;
import com.hiretrack.message_extractor.service.SourceMessageService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
@Slf4j
@ToString
public class MessageController {
    private final SourceMessageService sourceMessageService;
    @PostMapping
    public ResponseEntity<List<OutputMessage>> addSourceMessages(@RequestBody ChunkMessageDTO chunkMessageDTO){
        try{
            log.info("Entered message controller.");
            log.info("Request Body:: {}", chunkMessageDTO.toString());
            return new ResponseEntity<>(sourceMessageService.saveAndConvertToText(chunkMessageDTO),HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
