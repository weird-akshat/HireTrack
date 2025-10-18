package com.hiretrack.message_analyzer.message_analyzer.dto;


import lombok.Data;

@Data
public class InputMessage {
    private Long sourceId;
    private String text;
}
