package com.hiretrack.message_analyzer.message_analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse {
    private HttpStatus status;
    private String message;
}
