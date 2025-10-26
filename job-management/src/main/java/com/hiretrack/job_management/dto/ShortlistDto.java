package com.hiretrack.job_management.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
@Builder
public class ShortlistDto {
    private Long id;
    private long sourceId;
    private int shortlistRound;
    private long jobListingId;
    private List<String> studentDetails;
}
