package com.hiretrack.entity_manager.entity;

import com.hiretrack.entity_manager.dto.StudentDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Builder
public class Shortlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long sourceId;
    private long jobListingId;
    private int shortlistRound;
    @ElementCollection
    private List<StudentDto> list;
}
