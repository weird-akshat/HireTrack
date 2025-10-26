package com.hiretrack.job_management.mapper;

import com.hiretrack.job_management.dto.JobNotificationDto;
import com.hiretrack.job_management.entity.JobNotification;

public class JobNotificationMapper {

    public static JobNotificationDto toDto(JobNotification jobNotification){
        return JobNotificationDto.builder()
                .id(jobNotification.getId())
                .sourceId(jobNotification.getSourceId())
                .jobListingId(jobNotification.getJobListingId())
                .title(jobNotification.getTitle())
                .body(jobNotification.getBody())
                .build();
    }
}
