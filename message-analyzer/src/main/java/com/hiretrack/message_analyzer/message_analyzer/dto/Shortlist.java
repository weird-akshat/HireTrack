package com.hiretrack.message_analyzer.message_analyzer.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class Shortlist {
    private long sourceId;
    private String companyName;
    private String jobRole;
    private int shortlistRound;
    List<Student> studentDetails;
}
