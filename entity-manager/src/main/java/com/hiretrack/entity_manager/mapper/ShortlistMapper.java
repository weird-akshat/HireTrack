package com.hiretrack.entity_manager.mapper;

import com.hiretrack.entity_manager.dto.ShortlistDto;
import com.hiretrack.entity_manager.dto.StudentDto;
import com.hiretrack.entity_manager.entity.Shortlist;

import java.util.ArrayList;
import java.util.List;

public class ShortlistMapper {

    public static Shortlist toEntity(ShortlistDto shortlistDto, long jobListingId){
        List<StudentDto> students=shortlistDto.getStudentDetails();
        List<String> studentDetails=new ArrayList<>();

        for (StudentDto studentDto: students){
            studentDetails.add(studentDto.toString());
        }

        return Shortlist.builder()
                .shortlistRound(shortlistDto.getShortlistRound())
                .studentDetails(studentDetails)
                .sourceId(shortlistDto.getSourceId())
                .jobListingId(jobListingId)
                .build();
    }
}
