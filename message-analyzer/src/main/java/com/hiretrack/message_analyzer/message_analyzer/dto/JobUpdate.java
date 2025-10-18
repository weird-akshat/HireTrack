package com.hiretrack.message_analyzer.message_analyzer.dto;

import lombok.Data;

@Data
public class JobUpdate {
    private long sourceId;
    private String companyName;
    private String jobRole;
}
