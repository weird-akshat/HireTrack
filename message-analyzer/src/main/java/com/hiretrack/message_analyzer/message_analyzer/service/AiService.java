package com.hiretrack.message_analyzer.message_analyzer.service;

import com.hiretrack.message_analyzer.message_analyzer.dto.AiResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    private final ChatClient chatClient;

    public AiService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    public void getStructuredMessage(String text){
        AiResponse aiResponse = chatClient.prompt(text).user(u->u.text("Generate only the relevant structure based on the message, also create the notification for the same. For branches, have only the abbreviations with no spaces, no btech needed. For any time, give no timezone, localdatetime will be used in java. Notification should be short and precise, detailed info not needed (until the actual object type is notification, in which case give full details")).call().entity(AiResponse.class);

        System.out.println(aiResponse.getJobNotification());


    }


}
