package com.hiretrack.job_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder

@Entity
@AllArgsConstructor
public class JobNotification {
    public JobNotification() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private long sourceId;

    private long jobListingId;
    @Column(columnDefinition = "TEXT")

    public String title;
    @Column(columnDefinition = "TEXT")

    public String body;
}
