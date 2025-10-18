package com.hiretrack.message_analyzer.message_analyzer.service;

import com.hiretrack.message_analyzer.message_analyzer.dto.JobListing;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    private final ChatClient chatClient;

    public AiService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    public void getStructuredMessage(String text){
        JobListing jobListing = chatClient.prompt(text).call().entity(JobListing.class);

        System.out.println(jobListing);
    }


}
