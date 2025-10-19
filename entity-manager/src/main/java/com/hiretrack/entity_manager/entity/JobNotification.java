package com.hiretrack.entity_manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    public String title;
    public String body;
}
