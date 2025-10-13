package com.hiretrack.message_extractor.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor // required by JPA
@AllArgsConstructor // optional: useful for testing or mapping
@Builder
// optional: for clean object creation
@Entity


public class SourceMessage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String contentType;
    private long size;
    private LocalDateTime timeStamp;
    @Lob
    private String fileData;

}
