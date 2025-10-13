package com.hiretrack.message_extractor.Entity;

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
    @Column(columnDefinition = "LONGBLOB") 
    private byte[] fileData;

}
