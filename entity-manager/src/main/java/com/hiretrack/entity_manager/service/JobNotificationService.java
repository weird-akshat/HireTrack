package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.JobNotificationDto;
import com.hiretrack.entity_manager.entity.JobNotification;
import com.hiretrack.entity_manager.mapper.JobNotificationMapper;
import com.hiretrack.entity_manager.repo.JobNotificationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobNotificationService {
    private final JobNotificationRepo jobNotificationRepo;

    public void createNotification(JobNotificationDto jobNotificationDto){

        JobNotification jobNotifcation = JobNotificationMapper.toEntity(jobNotificationDto);

        jobNotificationRepo.save(jobNotifcation);

    }
}
