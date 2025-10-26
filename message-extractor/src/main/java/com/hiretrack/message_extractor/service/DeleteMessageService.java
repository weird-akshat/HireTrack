package com.hiretrack.message_extractor.service;

import com.hiretrack.message_extractor.client.EntityManagerClient;
import com.hiretrack.message_extractor.dtos.ApiResponse;
import com.hiretrack.message_extractor.dtos.SourceMessageDTO;
import com.hiretrack.message_extractor.entity.SourceMessage;
import com.hiretrack.message_extractor.repo.SourceMessageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteMessageService {
    private final SourceMessageRepo sourceMessageRepo;
    private final EntityManagerClient entityManagerClient;


    @Transactional
    public void deleteMessage(SourceMessage sourceMessage){
        log.info("Found the message to be deleted, sourceId: {}", sourceMessage.getId());
        sourceMessageRepo.deleteById(sourceMessage.getId());
        try{
            log.info("Sending request to entity manager to delete the messages");
            ApiResponse apiResponse = entityManagerClient.deleteMessage(sourceMessage.getId());
            log.info("Deletion request successfully processed");
        }
        catch (Exception e){
            log.error("Issue in deletion from entity manager");
            throw new RuntimeException("issue in deletion from entity manager");
        }
    }
}

