package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.repo.JobListingRepo;
import com.hiretrack.entity_manager.repo.JobNotificationRepo;
import com.hiretrack.entity_manager.repo.ShortlistRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeletionService {
    private final JobListingRepo jobListingRepo;
    private final JobNotificationRepo jobNotificationRepo;
    private final ShortlistRepo shortlistRepo;

    public void delete(Long sourceId){
        log.info("Started deleting");
        jobListingRepo.deleteBySourceId(sourceId);
        jobNotificationRepo.deleteBySourceId(sourceId);
        shortlistRepo.deleteBySourceId(sourceId);
        log.info("Deletion completed");
    }

}
