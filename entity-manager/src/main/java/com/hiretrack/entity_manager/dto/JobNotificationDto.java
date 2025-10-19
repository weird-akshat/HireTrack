package com.hiretrack.entity_manager.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JobNotificationDto {
    private long sourceId;
    public String title;
    public String body;
}
