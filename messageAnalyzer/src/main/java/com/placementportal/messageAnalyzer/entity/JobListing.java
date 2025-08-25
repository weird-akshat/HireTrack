package com.placementportal.messageAnalyzer.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Table(name = "jobListing")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String offerType;
    private String category;
    private String jobProfile;
    private String jobDescription;
    private String stipend;
    private String accommodationAllowance;
    private String onConversionCTC;

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
