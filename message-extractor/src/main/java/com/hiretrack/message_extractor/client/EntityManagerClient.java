package com.hiretrack.message_extractor.client;

import com.hiretrack.message_extractor.dtos.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ENTITY-MANAGER")
public interface EntityManagerClient {
    @DeleteMapping("/api")
    ApiResponse deleteMessage(@RequestBody Long sourceId);

}
