package com.hiretrack.entity_manager.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class JobNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private long sourceId;
    @Column(columnDefinition = "TEXT")

    public String title;
    @Column(columnDefinition = "TEXT")

    public String body;
}
