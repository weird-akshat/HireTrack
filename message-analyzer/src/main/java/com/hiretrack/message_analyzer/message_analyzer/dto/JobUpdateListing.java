package com.hiretrack.message_analyzer.message_analyzer.dto;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringSummary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Data
@ToString
public class JobUpdateListing {
    private long id;
    private long sourceId;
    private String companyName;
    private String offerType;
    private String category;
    private String jobProfile;
    private String jobDescription;
    private String stipend;
    private String accommodationAllowance;
    private String onConversionCTC;
    private double minCGPA;
    private int percentage10th;
    private int percentage12th;
    private int percentageDiploma;
    private LocalDateTime deadline;
    private List<String> eligibleBranches;
    private List<String> eligibilityCriteria;
    private List<String> workLocation;
    private Map<String,String> events;
    private Map<String,String> selectionProcess;
    private Map<String,String> registrationLinks;
}
