package com.hiretrack.message_extractor.controller;

import com.hiretrack.message_extractor.dtos.*;
import com.hiretrack.message_extractor.service.SourceMessageService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        log.info("Request received: {}",chunkMessageDTO.toString());
        try{
            log.info("Entered message controller.");
            log.info("Request Body:: {}", chunkMessageDTO.toString());
            return new ResponseEntity<>(sourceMessageService.saveAndConvertToText(chunkMessageDTO),HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping()
    public ResponseEntity<ApiResponse> deleteMessage(@RequestBody MessageDeletionRequest messageDeletionRequest){

        log.info("Received request to delete message: {}",messageDeletionRequest.toString());
        try{
            log.info("Received request to delete message: {}", messageDeletionRequest.toString());
            sourceMessageService.deleteMessage(messageDeletionRequest);
            log.info("Request processed successfully.");

            return new ResponseEntity<>(new ApiResponse(HttpStatus.NO_CONTENT,"Message deletion request processed"),HttpStatus.NO_CONTENT);

        }
        catch (Exception e){
            log.error("Error in deleting message");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }
    @PutMapping
    public ResponseEntity<ApiResponse> updateMessage(@RequestBody MessageEditRequest messageEditRequest){
        log.info("Received request to edit message: {}",messageEditRequest.toString());

        try{
            sourceMessageService.edit(messageEditRequest);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"edit message request completed"),HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Ran into error during the edit message request.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
