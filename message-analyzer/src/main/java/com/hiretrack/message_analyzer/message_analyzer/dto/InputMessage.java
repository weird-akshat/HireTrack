package com.hiretrack.message_analyzer.message_analyzer.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InputMessage {
    private Long sourceId;
    private String text;
}
