package com.hiretrack.message_extractor.Mapper;

import com.hiretrack.message_extractor.Entity.SourceMessage;
import com.hiretrack.message_extractor.dtos.SourceMessageDTO;

public class SourceMessageMapper {
    public static SourceMessage convertToEntity(SourceMessageDTO sourceMessageDTO){
        return SourceMessage.builder().contentType(sourceMessageDTO.getContentType()).size(sourceMessageDTO.getSize()).timeStamp(sourceMessageDTO.getTimeStamp()).fileData(sourceMessageDTO.getFileData()).build();
    }
}
