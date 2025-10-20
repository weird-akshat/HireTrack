package com.hiretrack.entity_manager.service;

import com.hiretrack.entity_manager.dto.ShortlistDto;
import com.hiretrack.entity_manager.entity.JobListing;
import com.hiretrack.entity_manager.entity.Shortlist;
import com.hiretrack.entity_manager.mapper.ShortlistMapper;
import com.hiretrack.entity_manager.repo.ShortlistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortlistService {
    private final ShortlistRepo shortlistRepo;
    private final LinkingService linkingService;
    public void createShortlist(ShortlistDto shortlistDto){
        JobListing jobListing = linkingService.findJobListing(shortlistDto.getCompanyName(),shortlistDto.getJobRole());
        System.out.println(jobListing.toString());
        Shortlist shortlist = ShortlistMapper.toEntity(shortlistDto,jobListing.getId());

        shortlistRepo.save(shortlist);

    }
}
