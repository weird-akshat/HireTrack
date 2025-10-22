package com.hiretrack.message_extractor.service;

import com.hiretrack.message_extractor.client.MessageExtractorClient;
import com.hiretrack.message_extractor.dtos.ApiResponse;
import com.hiretrack.message_extractor.dtos.ChunkMessageDTO;
import com.hiretrack.message_extractor.dtos.OutputMessage;
import com.hiretrack.message_extractor.entity.SourceMessage;
import com.hiretrack.message_extractor.mapper.SourceMessageMapper;
import com.hiretrack.message_extractor.repo.SourceMessageRepo;
import com.hiretrack.message_extractor.dtos.SourceMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SourceMessageService {
    private final SourceMessageRepo sourceMessageRepo;

    private final PdfReaderService pdfReaderService;
    private final ImageReaderService imageReaderService;
    private final ExcelReaderService excelReaderService;
    private final PptReaderService pptReaderService;
    private final MessageExtractorClient messageExtractorClient;

    public List<OutputMessage> saveAndConvertToText(ChunkMessageDTO chunkMessageDTO){
        List<OutputMessage> list= extractAll(storeMessages(chunkMessageDTO));
        log.info("Texts extracted");
        log.info("OutputMessages: {}", list.toString());

        log.info("Sending to message-extractor");
        ApiResponse response = messageExtractorClient.analyzeMessage(list);
        log.info("Response: {}", response);
        return list;
    }

    public List<SourceMessage> storeMessages(ChunkMessageDTO chunkMessageDTO){
        List<SourceMessage> messages = new ArrayList<>();

        for (SourceMessageDTO sourceMessageDTO : chunkMessageDTO.getMessages()){
            try {
                SourceMessage sourceMessage =  SourceMessageMapper.convertToEntity(sourceMessageDTO);
                sourceMessageRepo.save(sourceMessage);
                messages.add(sourceMessage);
            }
            catch (Exception e){
                log.error("Error in text extraction");
                throw new RuntimeException("Error in mapping to text");
            }

        }
        log.info("Text extracted from all the messages");
        return messages;
    }
    public List<OutputMessage> extractAll(List<SourceMessage> messages){
        List<OutputMessage> outputMessages = new ArrayList<>();
        for (SourceMessage sourceMessage : messages){
            OutputMessage outputMessage = new OutputMessage();



            outputMessage.setSourceId(sourceMessage.getId());
            try{
                if (sourceMessage.getContentType().equalsIgnoreCase("application/pdf")){
                    outputMessage.setText(pdfReaderService.extractText(sourceMessage));
                }
                else if (sourceMessage.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
                    outputMessage.setText(excelReaderService.extractText(sourceMessage));
                }
                else if (
                        sourceMessage.getContentType().equalsIgnoreCase("image/png") ||
                                sourceMessage.getContentType().equalsIgnoreCase("image/jpg") ||
                                sourceMessage.getContentType().equalsIgnoreCase("image/jpeg") ||
                                sourceMessage.getContentType().equalsIgnoreCase("image/gif") ||
                                sourceMessage.getContentType().equalsIgnoreCase("image/bmp") ||
                                sourceMessage.getContentType().equalsIgnoreCase("image/webp")
                ) {
                    outputMessage.setText(imageReaderService.extractText(sourceMessage));
                }
                else if (sourceMessage.getContentType().equalsIgnoreCase("text/plain")){
                    outputMessage.setText(new String(sourceMessage.getFileData(), StandardCharsets.UTF_8));
                }
                else if (sourceMessage.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.presentationml.presentation")){
                    outputMessage.setText(pptReaderService.extractText(sourceMessage));
                }
                if (sourceMessage.getCaption()!=null){
                    outputMessage.appendText("caption: "+sourceMessage.getCaption());

                }
            }
            catch (Exception e){
                log.error("Error in extracting text");
            }
            outputMessages.add(outputMessage);
        }
        System.out.println(outputMessages.get(0).getSourceId());
        return outputMessages;
    }

}
