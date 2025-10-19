package com.hiretrack.entity_manager.mapper;

import com.hiretrack.entity_manager.dto.JobNotificationDto;
import com.hiretrack.entity_manager.entity.JobNotification;

public class JobNotificationMapper {
    public static JobNotification toEntity(JobNotificationDto jobNotificationDto){
        return JobNotification.builder()
                .title(jobNotificationDto.getTitle())
                .body(jobNotificationDto.getBody())
                .sourceId(jobNotificationDto.getSourceId())
                .build();
    }

}
