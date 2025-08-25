package com.placementportal.messageAnalyzer.service;

import com.placementportal.messageAnalyzer.repo.JobListingRepo;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Service

public class JobListingService {

    private final JobListingRepo jobListingRepo;


    public JobListingService(JobListingRepo jobListingRepo){
        this.jobListingRepo = jobListingRepo;
    }
    public Map<String, Object> createJobListing(String jobListingMessage){
        final RestTemplate restTemplate = new RestTemplate();
        Dotenv dotenv= Dotenv.load();
        // WhatsApp message
        String apiKey= dotenv.get("GEMINI_API_KEY");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-goog-api-key", apiKey);

        // Build the request payload using Maps
        Map<String, Object> part = new HashMap<>();
        part.put("text", "Extract the following job listing into a structured JSON object following the JobListing schema:\n\n" + jobListingMessage);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", Collections.singletonList(part));

        Map<String, Object> toolFunction = new HashMap<>();
        toolFunction.put("name", "JobListing");

        Map<String, Object> properties = new HashMap<>();
        properties.put("companyName", Collections.singletonMap("type", "string"));
        properties.put("offerType", Collections.singletonMap("type", "string"));
        properties.put("category", Collections.singletonMap("type", "string"));
        properties.put("jobProfile", Collections.singletonMap("type", "string"));
        properties.put("jobDescription", Collections.singletonMap("type", "string"));
        properties.put("eligibleBranches", Map.of(
                "type", "array",
                "items", Map.of("type", "string")
        ));
        properties.put("eligibilityCriteria", Map.of(
                "type", "array",
                "items", Map.of("type", "string")
        ));

        properties.put("stipend", Collections.singletonMap("type", "string"));
        properties.put("deadline", Collections.singletonMap("type", "string"));

        properties.put("accommodationAllowance", Collections.singletonMap("type", "string"));
        properties.put("onConversionCTC", Collections.singletonMap("type", "string"));
        properties.put("workLocation", Map.of(
                "type", "array",
                "items", Map.of("type", "string")
        ));
        properties.put("events", Map.of("type", "object"));
        properties.put("selectionProcess", Map.of("type", "object"));
        properties.put("registrationLinks", Map.of("type", "object"));
        toolFunction.put("parameters", Map.of("type", "object", "properties", properties));

        Map<String, Object> tool = new HashMap<>();
        tool.put("function_declarations", Collections.singletonList(toolFunction));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", Collections.singletonList(content));
        requestBody.put("tools", Collections.singletonList(tool));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        
        return response.getBody();

    }
}
