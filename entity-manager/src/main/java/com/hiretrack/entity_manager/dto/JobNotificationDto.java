package com.hiretrack.entity_manager.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JobNotificationDto {
    private String companyName;
    private String jobRole;
    private long sourceId;
    public String title;
    public String body;
}
