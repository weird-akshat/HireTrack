package com.hiretrack.message_extractor.service;

import com.hiretrack.message_extractor.entity.SourceMessage;
import com.hiretrack.message_extractor.repo.SourceMessageRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageInfoService {

    private final SourceMessageRepo sourceMessageRepo;

    public void messageExists(Long sourceId){

        sourceMessageRepo.findById(sourceId).orElseThrow(()->new RuntimeException("Message not found"));

    }


}
