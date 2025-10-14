package com.hiretrack.message_extractor.dtos;

import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SourceMessageDTO {
    private String contentType;
    private long size;
    private LocalDateTime timeStamp;
    @Lob
    private byte[] fileData;
}
