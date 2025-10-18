package com.hiretrack.message_analyzer.message_analyzer.dto;

import lombok.Data;

@Data
public class JobNotification {
    private long sourceId;
    public String title;
    public String body;
}
