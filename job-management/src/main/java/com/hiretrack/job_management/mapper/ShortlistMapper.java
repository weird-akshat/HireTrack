package com.hiretrack.job_management.mapper;

import com.hiretrack.job_management.dto.ShortlistDto;
import com.hiretrack.job_management.entity.Shortlist;

public class ShortlistMapper {
    public static ShortlistDto toDto(Shortlist shortlist){
        return ShortlistDto.builder()
                .id(shortlist.getId())
                .sourceId(shortlist.getSourceId())
                .jobListingId(shortlist.getJobListingId())
                .shortlistRound(shortlist.getShortlistRound())
                .studentDetails(shortlist.getStudentDetails())
                .build();
    }
}
