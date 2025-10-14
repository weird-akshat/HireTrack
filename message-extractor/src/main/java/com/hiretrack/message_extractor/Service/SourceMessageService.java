//package com.hiretrack.message_extractor.Service;
//
//import com.hiretrack.message_extractor.Entity.SourceMessage;
//import com.hiretrack.message_extractor.Mapper.SourceMessageMapper;
//import com.hiretrack.message_extractor.Repo.SourceMessageRepo;
//import com.hiretrack.message_extractor.dtos.SourceMessageDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
////@AllArgsConstructor
//@Service
//@RequiredArgsConstructor
//public class SourceMessageService {
//    private final SourceMessageRepo sourceMessageRepo;
//
//
//    public void storeMessage(SourceMessageDTO sourceMessageDTO){
//        SourceMessage sourceMessage= SourceMessageMapper.convertToEntity(sourceMessageDTO);
//        sourceMessageRepo.save(sourceMessage);
//
//        return;
//    }
//
//}
