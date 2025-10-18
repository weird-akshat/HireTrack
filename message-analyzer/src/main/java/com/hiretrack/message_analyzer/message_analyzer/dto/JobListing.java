package com.hiretrack.message_analyzer.message_analyzer.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class JobListing {

    private String companyName;
    private String offerType;
    private String category;
    private String jobProfile;
    private String jobDescription;
    private String stipend;
    private String accommodationAllowance;
    private String onConversionCTC;
    private LocalDateTime deadline;
    private List<String> eligibleBranches;
    private List<String> eligibilityCriteria;
    private List<String> workLocation;
    private Map<String,String> events;
    private Map<String,String> selectionProcess;
    private Map<String,String> registrationLinks;
}
