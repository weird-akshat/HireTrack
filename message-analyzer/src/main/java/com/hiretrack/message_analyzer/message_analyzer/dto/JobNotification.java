package com.hiretrack.message_analyzer.message_analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class JobNotification {
    private String companyName;
    private String jobRole;
    private long sourceId;
    public String title;
    public String body;
}
