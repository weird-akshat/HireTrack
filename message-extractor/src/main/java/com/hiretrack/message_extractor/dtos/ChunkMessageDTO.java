package com.hiretrack.message_extractor.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class ChunkMessageDTO {
    private int messageCount;
    private List<SourceMessageDTO> messages;

}
