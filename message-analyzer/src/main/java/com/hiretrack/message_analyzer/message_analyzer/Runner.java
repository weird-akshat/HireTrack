package com.hiretrack.message_analyzer.message_analyzer;

import com.hiretrack.message_analyzer.message_analyzer.service.AiService;
import lombok.RequiredArgsConstructor;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.mistralai.MistralAiEmbeddingModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final AiService aiService;


    @Override
    public void run(String... args) throws Exception {
        String text=
""" 
Summer Internship – PayPal

Company Name: PayPal
Offer Type: Summer Internship
Category: Core
Job Profile: Software Engineer Intern

Eligibility:
* B.Tech CS (AIML)
* CGPA: 8.5 & above
* No backlogs

Stipend:
* ₹1,00,000 per month
* One-time sign-on bonus: ₹1,00,000

Work Location: Chennai / Bangalore

Registration Link: https://forms.gle/JrkiA2ZUXJh2cgFP8
Deadline: 3 PM, 16th October 2025

Note:
If a student receives a Pre-Placement Offer (PPO) through this internship, they will not be eligible to participate in the 7th-semester placement or internship process. A formal undertaking will be taken from the selected interns accordingly.""";
//
            aiService.getStructuredMessage(text);

    }
}
