package com.hiretrack.entity_manager.client;

import com.hiretrack.entity_manager.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MESSAGE-EXTRACTOR")
public interface MessageExtractorClient {
    @PostMapping("api/info")
    ApiResponse sourceMessageExists(@RequestBody Long sourceId);
}
