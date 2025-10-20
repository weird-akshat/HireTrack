package com.hiretrack.message_extractor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString

public class SourceMessage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String caption;
    private String contentType;
    private long size;
    private LocalDateTime timeStamp;
    @Lob
    private byte[] fileData;

}
