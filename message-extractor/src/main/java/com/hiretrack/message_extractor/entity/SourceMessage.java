package com.hiretrack.message_extractor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity


public class SourceMessage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String contentType;
    private long size;
    private LocalDateTime timeStamp;
    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] fileData;

}
