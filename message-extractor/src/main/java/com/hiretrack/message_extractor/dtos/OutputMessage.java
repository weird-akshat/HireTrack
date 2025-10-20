package com.hiretrack.message_extractor.dtos;


import lombok.Data;

@Data
public class OutputMessage {
    private Long sourceId;
    private String text;

    public void appendText(String string){
        text+=string;
    }
}
