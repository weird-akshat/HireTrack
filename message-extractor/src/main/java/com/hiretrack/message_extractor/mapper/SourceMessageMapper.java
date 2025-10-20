package com.hiretrack.message_extractor.mapper;

import com.hiretrack.message_extractor.entity.SourceMessage;
import com.hiretrack.message_extractor.dtos.SourceMessageDTO;

public class SourceMessageMapper {
    public static SourceMessage convertToEntity(SourceMessageDTO sourceMessageDTO){
        return SourceMessage.builder().contentType(sourceMessageDTO.getContentType()).caption(sourceMessageDTO.getCaption()).size(sourceMessageDTO.getSize()).timeStamp(sourceMessageDTO.getTimeStamp()).fileData(sourceMessageDTO.getFileData()).build();
    }
}
