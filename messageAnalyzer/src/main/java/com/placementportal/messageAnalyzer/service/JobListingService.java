package com.placementportal.messageAnalyzer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placementportal.messageAnalyzer.entity.JobListing;
import com.placementportal.messageAnalyzer.repo.JobListingRepo;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class JobListingService {

    private final JobListingRepo jobListingRepo;


    public JobListingService(JobListingRepo jobListingRepo){
        this.jobListingRepo = jobListingRepo;
    }
    @Transactional
    public JobListing createJobListing(String jobListingMessage){
        final RestTemplate restTemplate = new RestTemplate();
        Dotenv dotenv= Dotenv.load();
        // WhatsApp message
        String apiKey= dotenv.get("GEMINI_API_KEY");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-goog-api-key", apiKey);

        // Build the request payload using Maps
        Map<String, Object> part = new HashMap<>();
        part.put("text", "Extract the following job listing into JSON following the JobListing schema. " +
                "Ensure that fields `selectionProcess`, `events`, and `registrationLinks` are always returned as JSON objects (maps), even if only one entry exists. " +
                "All date fields should be in ISO 8601 format (yyyy-MM-dd'T'HH:mm:ss):\n\n" + jobListingMessage);



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
        properties.put("selectionProcess", Map.of(
                "type", "object",
                "description", "A map describing each selection round or step. Example: { 'Round 1': 'Online Test', 'Round 2': 'Interview' }"
        ));

        properties.put("events", Map.of(
                "type", "object",
                "description", "A map of events with names as keys and dates as values. Example: { 'Orientation': '2025-09-01' }"
        ));

        properties.put("registrationLinks", Map.of(
                "type", "object",
                "description", "A map of registration links with descriptions as keys and URLs as values. Example: { 'Form': 'https://link.com' }"
        ));
        toolFunction.put("parameters", Map.of("type", "object", "properties", properties));

        Map<String, Object> tool = new HashMap<>();
        tool.put("function_declarations", Collections.singletonList(toolFunction));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", Collections.singletonList(content));
        requestBody.put("tools", Collections.singletonList(tool));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);


        ObjectMapper mapper= new ObjectMapper();
        JsonNode root= mapper.valueToTree(response.getBody());
        String rawText = root.path("candidates").get(0)
                .path("content")
                .path("parts").get(0)
                .path("text")
                .asText();
        System.out.println(rawText);
        String cleanText = rawText.replaceAll("```json\\n?", "")
                .replaceAll("```", "")
                .trim();
        System.out.println(cleanText);
        try{
            Map<String, Object> jobMap = mapper.readValue(cleanText, Map.class);
            JobListing jobListing = new JobListing();
            jobListing.setCompanyName((String) jobMap.get("companyName"));
            jobListing.setOfferType((String) jobMap.get("offerType"));
            jobListing.setCategory((String) jobMap.get("category"));
            jobListing.setJobProfile((String) jobMap.get("jobProfile"));
            jobListing.setJobDescription((String) jobMap.get("jobDescription"));
            jobListing.setStipend((String) jobMap.get("stipend"));
            jobListing.setAccommodationAllowance((String) jobMap.get("accommodationAllowance"));
            jobListing.setOnConversionCTC((String) jobMap.get("onConversionCTC"));
            System.out.print("done");
// Lists
            jobListing.setEligibleBranches((List<String>) jobMap.get("eligibleBranches"));
            jobListing.setEligibilityCriteria((List<String>) jobMap.get("eligibilityCriteria"));
            jobListing.setWorkLocation((List<String>) jobMap.get("workLocation"));
//            System.out.println("done1");
// Maps
            // Usage
            jobListing.setEvents(convertToStringMap((Map<?,?>) jobMap.get("events")));
            jobListing.setSelectionProcess(convertToStringMap((Map<?,?>) jobMap.get("selectionProcess")));
            jobListing.setRegistrationLinks(convertToStringMap((Map<?,?>) jobMap.get("registrationLinks")));

//            System.out.println("done2");
// Deadline (ISO 8601 string -> LocalDateTime)
            String deadlineStr = (String) jobMap.get("deadline");
            if (deadlineStr != null && !deadlineStr.isEmpty()) {
                jobListing.setDeadline(LocalDateTime.parse(deadlineStr, DateTimeFormatter.ISO_DATE_TIME));
            }
//            System.out.println("done3");




// Now jobListing is fully populated
            jobListingRepo.save(jobListing);
            return jobListing;

        }
        catch (Exception e){
            System.out.println("Exception occurred in JobListingService class");

            return null;

        }

//        return null;



    }

    private Map<String,String> convertToStringMap(Map<?,?> map) {
        if (map == null) return null;
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().toString(),
                        e -> e.getValue() != null ? e.getValue().toString() : null
                ));
    }

}
