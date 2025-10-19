package com.hiretrack.entity_manager.dto;

import lombok.Data;

@Data
public class JobUpdateDto {
    private long sourceId;
    private String companyName;
    private String jobRole;
}
