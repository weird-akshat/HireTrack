package com.hiretrack.message_extractor.dtos;

import lombok.Data;
import lombok.ToString;


import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class MessageDeletionRequest {
    LocalDateTime timestamp;
    List<SourceMessageDTO> messageList;
}
