package com.hiretrack.message_analyzer.message_analyzer.service;

import com.hiretrack.message_analyzer.message_analyzer.client.EntityManagerClient;
import com.hiretrack.message_analyzer.message_analyzer.dto.AiResponse;
import com.hiretrack.message_analyzer.message_analyzer.dto.Shortlist;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import static com.hiretrack.message_analyzer.message_analyzer.enums.ResponseType.JOB_LISTING;

@Service
public class AiService {
    private final ChatClient chatClient;
    private final EntityManagerClient entityManagerClient;
    public AiService(ChatClient.Builder chatClientBuilder, EntityManagerClient entityManagerClient){
        this.chatClient = chatClientBuilder.build();
        this.entityManagerClient= entityManagerClient;
    }

    public void getStructuredMessage(String text){
        String prompt = "Extract only the relevant fields from the message. Normalize company names to their standard form (remove suffixes like 'OT', 'Inc.', etc.). For branches, use abbreviations with no spaces and exclude 'BTech'. For any time, omit timezone; use LocalDateTime. Create a short, precise notification. Include detailed info only if the object type is 'notification'. Set response type to the most appropriate enum. leave field null if unknown. Return minimal structure only; Any update regarding changes for a job listing should be classified as jobupdate for eg the online test (OT) postponoement, Interview postponement, new details regarding an exisiting opportunity .";

        AiResponse aiResponse = chatClient.prompt(text).user(u->u.text(prompt)).call().entity(AiResponse.class);

        System.out.println(aiResponse.getResponseType());
        System.out.println(aiResponse.getJobListing());
        System.out.println(aiResponse.getJobUpdate());
        System.out.println(aiResponse.getJobNotification());
        Shortlist shortlist = aiResponse.getShortlist();
        if (shortlist!=null){
            System.out.println(shortlist.getStudentDetails());
        }
        if (aiResponse.getResponseType()==JOB_LISTING){
            entityManagerClient.createJobListing(aiResponse.getJobListing());
        }
        entityManagerClient.createNotification(aiResponse.getJobNotification());

    }


}
