package com.hiretrack.job_management.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JobNotificationDto {
    private long id;
    private long sourceId;
    private long jobListingId;
    private String title;
    private String body;
}
