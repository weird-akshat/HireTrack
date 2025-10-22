package com.hiretrack.message_extractor.dtos;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
public class ApiResponse {
    private HttpStatus status;
    private String message;
}
