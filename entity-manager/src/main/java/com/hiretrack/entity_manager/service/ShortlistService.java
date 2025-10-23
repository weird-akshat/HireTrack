package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.ShortlistDto;
import com.hiretrack.entity_manager.entity.JobListing;
import com.hiretrack.entity_manager.entity.Shortlist;
import com.hiretrack.entity_manager.mapper.ShortlistMapper;
import com.hiretrack.entity_manager.repo.ShortlistRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShortlistService {
    private final ShortlistRepo shortlistRepo;
    private final LinkingService linkingService;
    public void createShortlist(ShortlistDto shortlistDto){
        try{
            log.info("Finding joblisting for the shortlist");
            JobListing jobListing = linkingService.findJobListing(shortlistDto.getCompanyName(),shortlistDto.getJobRole());
            log.info("Job listing found\nBeginning to map shortlist to entity");
            Shortlist shortlist = ShortlistMapper.toEntity(shortlistDto,jobListing.getId());
            log.info("Shortlist mapped");
            log.info("Saving shortlist");
            shortlistRepo.save(shortlist);
            log.info("Shortlist saved");

        }

        catch (Exception e){
            log.error("Error in shortlisting service");
            throw new RuntimeException();
        }


    }
}
