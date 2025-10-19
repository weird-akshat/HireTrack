package com.hiretrack.message_extractor.client;

import com.hiretrack.message_extractor.dtos.OutputMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="MESSAGE-ANALYZER")
public interface MessageExtractorClient {
    @PostMapping("/api")
    void analyzeMessage(@RequestBody List<OutputMessage> list);
}
