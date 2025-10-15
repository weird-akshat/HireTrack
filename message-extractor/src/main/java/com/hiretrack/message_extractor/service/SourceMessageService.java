package com.hiretrack.message_extractor.service;

import com.hiretrack.message_extractor.entity.SourceMessage;
import com.hiretrack.message_extractor.mapper.SourceMessageMapper;
import com.hiretrack.message_extractor.repo.SourceMessageRepo;
import com.hiretrack.message_extractor.dtos.SourceMessageDTO;
import com.hiretrack.message_extractor.repo.SourceMessageRepo;
import com.hiretrack.message_extractor.service.ExcelReaderService;
import com.hiretrack.message_extractor.service.ImageReaderService;
import com.hiretrack.message_extractor.service.PdfReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//@AllArgsConstructor
@Service
@RequiredArgsConstructor
public class SourceMessageService {
    private final SourceMessageRepo sourceMessageRepo;

    private final PdfReaderService pdfReaderService;
    private final ImageReaderService imageReaderService;
    private final ExcelReaderService excelReaderService;

    public void storeMessage(SourceMessageDTO sourceMessageDTO){
        SourceMessage sourceMessage= SourceMessageMapper.convertToEntity(sourceMessageDTO);
        sourceMessageRepo.save(sourceMessage);

        return;
    }

}
