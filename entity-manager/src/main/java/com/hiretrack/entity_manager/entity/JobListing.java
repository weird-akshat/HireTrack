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
    private long sourceId;

    @Column(columnDefinition = "TEXT")
    private String companyName;
    @Column(columnDefinition = "TEXT")
    private String offerType;

    @Column(columnDefinition = "TEXT")

    private String category;

    @Column(columnDefinition = "TEXT")

    private String jobProfile;

    @Column(columnDefinition = "TEXT")

    private String jobDescription;

    @Column(columnDefinition = "TEXT")

    private String stipend;

    @Column(columnDefinition = "TEXT")

    private String accommodationAllowance;

    @Column(columnDefinition = "TEXT")

    private String onConversionCTC;
    private double minCGPA;
    private int percentage10th;
    private int percentage12th;
    private int percentageDiploma;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
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
