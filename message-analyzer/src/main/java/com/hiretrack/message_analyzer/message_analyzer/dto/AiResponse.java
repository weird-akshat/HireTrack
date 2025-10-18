package com.hiretrack.message_analyzer.message_analyzer.dto;

import com.hiretrack.message_analyzer.message_analyzer.enums.ResponseType;
import lombok.Data;

@Data
public class AiResponse {
    private ResponseType responseType;
    private JobListing jobListing;
    private JobNotification jobNotification;
    private  JobUpdate jobUpdate;
    private Shortlist shortlist;

}
