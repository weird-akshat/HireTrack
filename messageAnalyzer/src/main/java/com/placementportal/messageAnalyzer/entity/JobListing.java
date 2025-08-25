package com.placementportal.messageAnalyzer.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


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
    private List<String> eligibleBranches;
    private List<String> eligibilityCriteria;
    private String stipend;
    private LocalDateTime deadline;
    private String accommodationAllowance;
    private String onConversionCTC;
    private List<String> workLocation;
    private HashMap<String,String> events;
    private HashMap<String,String> selectionProcess;
    private HashMap<String,String> registrationLinks;


}
