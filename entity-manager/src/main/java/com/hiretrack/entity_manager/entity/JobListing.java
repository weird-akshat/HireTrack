package com.hiretrack.entity_manager.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
@Data
@Entity
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
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
    @ElementCollection
    private List<String> eligibleBranches;
    @ElementCollection
    private List<String> eligibilityCriteria;
    @ElementCollection
    private List<String> workLocation;
    @ElementCollection
    private Map<String,String> events;
    @ElementCollection
    private Map<String,String> selectionProcess;
    @ElementCollection
    private Map<String,String> registrationLinks;
}
