package com.hiretrack.message_extractor.controller;

import com.hiretrack.message_extractor.dtos.ChunkMessageDTO;
import com.hiretrack.message_extractor.service.SourceMessageService;
import jakarta.ws.rs.POST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final SourceMessageService sourceMessageService;
    @POST
    public void addSourceMessages(ChunkMessageDTO chunkMessageDTO){

    }
}
