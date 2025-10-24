package com.hiretrack.message_extractor.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
@AllArgsConstructor
public class ApiResponse {
    private HttpStatus status;
    private String message;
}
