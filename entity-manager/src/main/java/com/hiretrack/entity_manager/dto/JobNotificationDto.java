package com.hiretrack.entity_manager.dto;

import lombok.Data;

@Data
public class JobNotificationDto {
    private long sourceId;
    public String title;
    public String body;
}
