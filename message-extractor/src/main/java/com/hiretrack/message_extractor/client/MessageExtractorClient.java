package com.hiretrack.message_extractor.client;

import com.hiretrack.message_extractor.dtos.ApiResponse;
import com.hiretrack.message_extractor.dtos.OutputMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="MESSAGE-ANALYZER")
public interface MessageExtractorClient {
    @PostMapping("/api")
    ApiResponse analyzeMessage(@RequestBody List<OutputMessage> list);
}
