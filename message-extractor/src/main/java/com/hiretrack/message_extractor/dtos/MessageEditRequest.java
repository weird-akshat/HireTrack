package com.hiretrack.message_extractor.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MessageEditRequest {
    private LocalDateTime timestamp;
    private String originalText;
    private String editedText;
}
