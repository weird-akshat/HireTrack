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
        AiResponse aiResponse = chatClient.prompt(text).user(u->u.text("Generate only the relevant structure based on the message, also create the notification for the same. For branches, have only the abbreviations with no spaces, no btech needed. For any time, give no timezone, localdatetime will be used in java. Notification should be short and precise, detailed info not needed (until the actual object type is notification, in which case give full details. Set the response type to the most appropriate enum. Define each field within the limiation of varchar(255)")).call().entity(AiResponse.class);

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
