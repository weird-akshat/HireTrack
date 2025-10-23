package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.JobNotificationDto;
import com.hiretrack.entity_manager.entity.JobListing;
import com.hiretrack.entity_manager.entity.JobNotification;
import com.hiretrack.entity_manager.mapper.JobNotificationMapper;
import com.hiretrack.entity_manager.repo.JobNotificationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobNotificationService {
    private final JobNotificationRepo jobNotificationRepo;
    private final LinkingService linkingService;

    public void createNotification(JobNotificationDto jobNotificationDto){
        try{
            log.info("Attempting to create job notification");

            log.info("Finding the correct job listing");
            JobNotification jobNotifcation;
            try{
                JobListing jobListing =  linkingService.findJobListing(jobNotificationDto);
                log.info("Found the correct job listing");
                log.info("Mapping notification to entity");
                jobNotifcation= JobNotificationMapper.toEntity(jobNotificationDto,jobListing);
            }
            catch (Exception e){
                log.info("No job listing found: General notification");
                log.info("Mapping notification to entity");
                jobNotifcation= JobNotificationMapper.toEntity(jobNotificationDto);

            }


            jobNotificationRepo.save(jobNotifcation);
        }
        catch (Exception e){
            log.info("Error in notification service");
            throw new RuntimeException();
        }





    }
}
