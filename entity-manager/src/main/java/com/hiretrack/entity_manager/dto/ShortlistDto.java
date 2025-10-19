package com.hiretrack.entity_manager.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class ShortlistDto {
    private long sourceId;
    private String companyName;
    private String jobRole;
    List<Map<String, String>> studentDetails;
}
