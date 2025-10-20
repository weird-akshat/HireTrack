package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.JobNotificationDto;
import com.hiretrack.entity_manager.entity.JobListing;
import com.hiretrack.entity_manager.entity.JobNotification;
import com.hiretrack.entity_manager.mapper.JobNotificationMapper;
import com.hiretrack.entity_manager.repo.JobNotificationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobNotificationService {
    private final JobNotificationRepo jobNotificationRepo;
    private final LinkingService linkingService;

    public void createNotification(JobNotificationDto jobNotificationDto){
        JobListing jobListing =  linkingService.findJobListing(jobNotificationDto);
        JobNotification jobNotifcation;
        if (jobListing==null){
             jobNotifcation= JobNotificationMapper.toEntity(jobNotificationDto);
        }
        else{
            jobNotifcation= JobNotificationMapper.toEntity(jobNotificationDto,jobListing);
        }



        jobNotificationRepo.save(jobNotifcation);

    }
}
