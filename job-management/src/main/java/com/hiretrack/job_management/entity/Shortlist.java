package com.hiretrack.job_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shortlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long sourceId;
    private long jobListingId;
    private int shortlistRound;
    private List<String> studentDetails;
}
